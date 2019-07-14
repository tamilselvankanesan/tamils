package cmecf.success;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class TestFileCopy {
    public static final ExecutorService CACHED_EXECUTORSERVICE = Executors.newCachedThreadPool();
    
    public static void main(String args[]) throws Exception {
        final String targetFolder = args.length==1?args[0]:System.getProperty("java.io.tmpdir");
        System.out.println("TargetFolder: "+targetFolder);
        
        ForkJoinPool fjPool = new ForkJoinPool(1);
        fjPool.submit(()->{
            IntStream.range(0, 1).parallel().forEach((index)->{
                String fileName = "testBigFile_"+index+".dat";
                try {
                    
                    testCreateBigFile(fileName);
                    testFileCopy(fileName, targetFolder);
                    Files.deleteIfExists(Paths.get(System.getProperty("java.io.tmpdir"), fileName));
                }
                catch(Throwable th) {
                    System.err.println("Error copying file with fileName: "+fileName + " : "+th.getMessage());
                    th.printStackTrace(System.err);
                }
            });
        }).join();
    }
    
    public static void testFileCopy(String fileName, String targetFolder) throws IOException {
        Path source = Paths.get(System.getProperty("java.io.tmpdir"), fileName);
        Path target = Paths.get(targetFolder, fileName);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Finished copying file with fileName: "+fileName);
    }
    
    public static void testCreateBigFile(String fileName) throws IOException {
        final Semaphore concurrencySemaphore = new Semaphore(5);
        Path segmentFile = Paths.get(System.getProperty("java.io.tmpdir"), fileName);
        long fileSize = 3L * 1024L * 1024L * 1024L;
        int blockSize = 10 * 1024 * 1024;
        int loopCount = (int)Math.floorDiv(fileSize, blockSize);
        
        try (RandomAccessFile raf = new RandomAccessFile(segmentFile.toFile(), "rw")) {
            raf.setLength(fileSize);
            try (FileChannel fc = raf.getChannel()) {
                for(int i = 0;i<loopCount;i++) {
                    final long startPosition =  1L * blockSize * i;
                    concurrencySemaphore.acquireUninterruptibly();
                    CACHED_EXECUTORSERVICE.submit(()->{
                        writeTemplateData(fileName, raf, startPosition, blockSize, concurrencySemaphore);
                    });
                }
            }
            finally {
                concurrencySemaphore.acquireUninterruptibly(5);
            }
        }
    }
    
    private static final void writeTemplateData(String fileName, RandomAccessFile raf, long startPosition, int blockSize, Semaphore concurrencySemaphore) {
        try {
            FileChannel fc = raf.getChannel();
            
            byte[] EMPTY_RECORD = new byte[blockSize/256];
            
            MappedByteBuffer mappedByteBuffer = fc.map(MapMode.READ_WRITE, startPosition, blockSize);
            IntStream.range(0, 256).forEach((recordIndex) -> {
                try {
                    mappedByteBuffer.position((int) (recordIndex * EMPTY_RECORD.length));
                    mappedByteBuffer.put(EMPTY_RECORD, 0, EMPTY_RECORD.length);
                } catch (Throwable th) {
                    System.err.println("Error in TestFileCopy.writeTemplateData empty record for fileName: " + fileName + ", startPosition: " + startPosition +", recordIndex: "+recordIndex + " : " + th.getMessage());
                    th.printStackTrace(System.err);
                }
            });
            
            mappedByteBuffer.force();
        } catch (Throwable th) {
            System.err.println("Error in TestFileCopy.writeTemplateData empty record for fileName: " + fileName + ", startPosition: " + startPosition + " : " + th.getMessage());
            th.printStackTrace(System.err);
        } 
        finally {
            concurrencySemaphore.release();
        }
    }
}
