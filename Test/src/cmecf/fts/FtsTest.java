package cmecf.fts;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class FtsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println(getFilteredText(
		 * "Hearing Held for the reasons stated on the record. The Motion for Relief From Judgment/Order Under Rule 9024, ECF No. 204 is granted. A proposed order to be submitted by October 22, 2019. The Motion to Withdraw, ECF No. 205 is held until October 22, 2019 as Counsel to file something on docket regarding the Motion <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013197917'>204</a> Motion for Relief from Judgment/Order Under Rule 9024 filed by Debtor Charlene Parks, <a href='https://ecf.ctb.uscourts.gov/doc1/040013200798'>205</a> Motion to Withdraw as Attorney filed by Debtor Charlene Parks)</font>. (Pettway, Christina)"
		 * , true));
		 */
		try {
//			another("Hearing Held for the reasons stated on the record. The Motion for Relief From Judgment/Order Under Rule 9024, ECF No. 204 is granted. A proposed order to be submitted by October 22, 2019. The Motion to Withdraw, ECF No. 205 is held until October 22, 2019 as Counsel to file something on docket regarding the Motion <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013197917'>204</a> Motion for Relief from Judgment/Order Under Rule 9024 filed by Debtor Charlene Parks, <a href='https://ecf.ctb.uscourts.gov/doc1/040013200798'>205</a> Motion to Withdraw as Attorney filed by Debtor Charlene Parks)</font>. (Pettway, Christina)");
			another("<b>ORDER DENYING MOTION FOR ORDER FOR EMERGENCY RELIEF.</b> The Motion for Order for Emergency Relief, ECF No. <a href='https://ecf.ctb.uscourts.gov/doc1/040013249190'>6</a>, is <b>DENIED</b>. Although the Debtor and her brother-in-law appeared at an emergency hearing held on September 24, 2019, they were not able to show any cause for the relief requested in the Motion and failed to inform the Court that an Order for In Rem Relief regarding the property known as 106B Comstock Hill Road, Norwalk, Connecticut, was entered in the Debtor&#039;s husband&#039;s case on September 18, 2018, Case No. 18-50975, which is evidence that the Debtor filed her Chapter 13 petition in bad faith. During the September 24th hearing, the Debtor&#039;s brother-in-law indicated that Attorney David Scalzi provided advice to the Debtor and/or her family in connection with the filing of this Chapter 13 case and counsel for Bank of New York as Trustee for the Certificateholders of CHL Mortgage Pass-Through Trust 2003-15 c/o Countrywide Home Loans, Inc. and the State Court Marshal testified and represented to the Court that Attorney David Scalzi informed them that the Debtor has filed a Chapter 13 petition on September 23, 2019 and that the State Court Ordered eviction was therefore stayed. At or before 4:00 p.m. on September 26, 2019, the Clerk&#039;s Office shall serve a copy of this Order on the Pro Se Debtor at the address listed on her petition and on Attorney David Scalzi via his CM/ECF email address and Attorney Scalzi will be the subject of a further Order imposing sanctions against him for his continued and repeated misconduct before this Court. <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013249190'>6</a>)</font>. Signed by Chief Judge Julie A. Manning on September 26, 2019. (Senteio, Renee)");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getFilteredText(String orgText, boolean useBR) {
		String cEmbeddedText = "something";

		if (orgText == null) {
			return "";
		}
		// String returnValue = orgText.replaceAll ("<", "&lt;" ) .replaceAll (
		// ">","&gt;" ) .replaceAll ( "&lt;i&gt;", "<i>" ) .replaceAll (
		// "&lt;/i&gt;","</i>" );
		// if ( useBR ) {
		// returnValue = returnValue.replaceAll ( "\n", "<br>" );
		// }
		orgText = orgText.replaceAll("<!--", "&lt;!--").replaceAll("-->", "--&gt;");

		String returnValue = orgText;
		String begSpan = "<strong><SPAN STYLE=\"background:yellow\">";
		String endSpan = "</SPAN></strong>";
		String pattern = cEmbeddedText;
		if (!pattern.startsWith("\"")) {
			String[] terms = cEmbeddedText.split("\\s");
			if (terms.length > 1) {
				StringBuffer pat = new StringBuffer();
				for (int i = 0; i < terms.length; i++) {
					int starIndex = terms[i].indexOf('*');
					if (starIndex > 0 && starIndex < terms[i].length() - 1) {
						pat.append(terms[i].replace("*", "\\w*"));
					} else {
						pat.append("\\b").append(terms[i]);
					}
					if (i < terms.length - 1) {
						pat.append("|");
					}
				}
				pattern = pat.toString();
			}
		} else {
			pattern = pattern.replaceAll("\"", "");
		}
		Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(returnValue);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			int st = m.start();
			int en = m.end();
			m.appendReplacement(sb, begSpan + returnValue.substring(st, en) + endSpan);
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	private static void another(String dtext) throws IOException, ParseException {
		String cEmbeddedText = "bad faith";
		PerFieldAnalyzerWrapper _PFAW = new PerFieldAnalyzerWrapper ( new KeywordAnalyzer () );
		String[] textSource = { "docket-text", "document-text" };

        QueryParser _QP = null;
        int index = 2;
        if ( index == 2 )
        {
            for ( int i = 0; i < index; i++ )
            {
                _PFAW.addAnalyzer ( textSource[i], new StandardAnalyzer () );
            }
            _QP = new MultiFieldQueryParser ( textSource, _PFAW );
        }
		
		Query que = null;
		TopDocCollector collector = new TopDocCollector(2000);
		BooleanQuery _BooleanQuery = new BooleanQuery ();
		
		Query _EmbeddedTextQuery = null;
        _EmbeddedTextQuery = _QP.parse ( cEmbeddedText );
		_BooleanQuery.add ( _EmbeddedTextQuery,
				   BooleanClause.Occur.MUST );
		
		
		 BooleanQuery _pridQ = new BooleanQuery();
         _pridQ.setMinimumNumberShouldMatch(1);
         
         
         _pridQ.add(
                 new TermQuery(
                 new Term(
                 "Judge",
                 "")),
                 BooleanClause.Occur.SHOULD);
		_BooleanQuery.add(_pridQ,
				  BooleanClause.Occur.MUST);
		
		
		Directory _Directory = FSDirectory.getDirectory ( "C:\\Tamil\\temp\\cmcb_fts" );
		IndexReader _IndexReader = IndexReader.open ( _Directory, true );
		IndexSearcher _IndexSearcher = new IndexSearcher ( _Directory );
		que =_BooleanQuery;
		_IndexSearcher.search ( que,collector);
		
		
		
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		int totalHits = hits.length;
		//adding configuaral hits per page
		//sections = totalHits/10;
		int cResultsPerPage = 10;
		int cStartIndex = 0;
		int limit,section,cEndIndex = 0;
		int sections = totalHits/cResultsPerPage;
		if (sections > 0) {
			limit = cResultsPerPage;
			section = 1;
			if (totalHits%cResultsPerPage != 0) {
				sections++;
			}
		} else {
			sections = 1;
			section = 1;
			limit = hits.length;
		}
		int lower = 0;
		if (cStartIndex > 0) {
			lower = cStartIndex;
		}
		if (cEndIndex > 0) {
			limit = cEndIndex;
			section = cEndIndex/cResultsPerPage;
		}
        //Iterator _I = _Hits.iterator ();
        //for ( int i = 0; ( i < 2000 ) && _I.hasNext (); i++ )
		if (cEndIndex == hits.length && cEndIndex < 2000 ) {
			//adding appellate 1.22 changes
			if (totalHits%cResultsPerPage!=0){
				section++;
			}
		}
		
		
		
		StringReader dktTextReader = new StringReader( ( dtext ) );
		
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span class=\"highlight\">", "</span>");
		Highlighter dktextHighlighter = new Highlighter(formatter,
                new QueryScorer(_EmbeddedTextQuery.rewrite(_IndexReader)));
		dtext = dktextHighlighter.getBestFragments(
                new StandardAnalyzer().tokenStream("docket-text", dktTextReader),
                dtext,
                25,
                "..."
            );
		System.out.println(StringUtil.Meta(dtext));
//		_DrsDocument.setDocketText ();
		
		for (int i = lower; i < limit; i++) {
			int docId = hits[i].doc;
            //Document _D = _H.getDocument ();
			Document _D = _IndexSearcher.doc(docId);
			System.out.println(dtext);
			dktTextReader.close ();
			if ( dtext.isEmpty() ) {
	            dtext = _D.get("docket-text");
	        }
		}
		
		
		
	}

}
