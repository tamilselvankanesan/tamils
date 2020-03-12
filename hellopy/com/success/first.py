'''
Created on Mar 10, 2020

@author: btamilselvan
'''
import re
from re import match

def hw():
    print('''hello world testing ''')


def add():
    print('add two number: ')
    num1 = int(input('enter number 1: '))
    num2 = int(input('enter number 2: '))
    print('sum is %d' %(num1+num2))

def sr():
    print('\n Find the Square Root: ')
    for i in range(17):
        print('Square root of %d is %d' %(i, i**.5))

def swap():
    print("swap two numbers.. \n")
    num1 = int(input("enter number 1:"))
    num2 = int(input("enter number 2:"))
    num1,num2 = num2,num1
    print("after swapping num1 is {}, num2 is {}".format(num1, num2))
    
def listfun():
    
    mList = [0, 1, 2,4]
    print(mList.__dir__())
    print('original list', mList)
    mList.reverse()
    print('reversed list', *mList)
    
    mList = [range(1, 50, 10)]
    print(mList)
    
    mList = [i*2 for i in range(10)]
    print(mList)
    
    for i in range(10):
        mList.append(i)
    print(mList)

def strfun():
    aList = ['hello world', 'whatiss']
#     mystr = ["{:>2d}".format(i) for i in range(10)]
    mystr = ["{:>020}".format(i) for i in aList]
    print(mystr)
    resStr = '';
    for i in mystr:
        resStr +=i
#     print(resStr)
    resStr.join(mystr)
#     print(resStr)


def strFormat(start, end):
    for i in range(start, end):
        print("{0:^4d} {2:<4d} {1:>4d} {3:<4d}".format(i**1, i**2, i**3, i**4))

def countAllUrl(aName):
    result_set=set()
#     print('before start')
    tFile = open(aName, 'r')
    count = 0
    pattern='(GET|POST|PUT|DELETE) (.+) HTTP'
    sPattern='(.+)\?'
    for line in tFile:
        match = re.search(pattern, line)
        if match:
            mystr = match.group(2)
            index = mystr.find('server.php')
            if index>0:
                smatch = re.search(sPattern, mystr)
                if smatch:
                    result_set.add(smatch.group(1))
            else:
                result_set.add(match.group(2))
                count +=1
#         if count==1000:
#             break
#         print(line)
#     tFile.read()
    tFile.close()
#     print(result_set)
    for i in result_set:
#         print(i)
        pass
    return count

def countUrl(url, aName):
#     print('before start')
    tFile = open(aName, 'r')
    count = 0
    pattern='(GET|POST|PUT|DELETE) (.+) HTTP'
    for line in tFile:
        
        match = re.search(pattern, line)
        if match:
            mystr = match.group(2)
            index = mystr.find(url)
            if index>0:
                count +=1
    tFile.close()
    return count
        
def unimplemented():
    pass

def extract(aName):
#     aFile = open(file='C:\\Tamil\\temp\\09Mar15And16LiveAccess.log', mode='w')
#     aFile.write('Hello world')
#     aFile.close()
    with open(file=aName, mode='w') as bFile:
        aFile = open('C:\\Tamil\\temp\\09MarLiveAccess.log', mode='r')
        aPattern = '(2020:15|2020:16)'
        for aLine in aFile:
            aMatch = re.search(aPattern, aLine)
            if aMatch:
                bFile.write(aLine)   
        bFile.close()
        aFile.close()
    

def extractUnique(aName):
    unique = set()
    with open(aName, 'r') as aFile:
        aPattern = '(GET|POST|DELETE|PUT) \/([\/\w-]+)'
        for aLine in aFile:
            aMatch = re.search(aPattern, aLine)
            if aMatch:
                unique.add(aMatch.group(2))
    return unique

def dictTest():
    pass

def tupleTest():
    aTuple = (1,2,3)
    print(*aTuple)
    print(aTuple)
    print(aTuple[1])
    aTuple.__getitem__(2)
# count = countAllUrl('C:\\Tamil\\temp\\09MarLiveAccess.log')
# print('others count %d' %count)
# count = countUrl('server.php', 'C:\\Tamil\\temp\\09MarLiveAccess.log')
# print('server.php count %d' %count)
# bFile = 'C:\\Tamil\\temp\\09Mar15And16LiveAccess.log'
# extract(bFile)
# count = countUrl('server.php', bFile)
# print('server.php count %d' %count)
# 
# count = countAllUrl(bFile)
# print('others count %d' %count)
# 
# aUnique = extractUnique(bFile)
# strfun()
# strFormat(3, 7)
tupleTest()
# print(aUnique)
# for i in aUnique:
#     print(i)

