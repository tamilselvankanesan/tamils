package cmecf.fts;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import org.apache.lucene.search.ConstantScoreRangeQuery;
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
	private static final String UNSELECTED = "UNSELECTED";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println(getFilteredText(
		 * "Hearing Held for the reasons stated on the record. The Motion for Relief From Judgment/Order Under Rule 9024, ECF No. 204 is granted. A proposed order to be submitted by October 22, 2019. The Motion to Withdraw, ECF No. 205 is held until October 22, 2019 as Counsel to file something on docket regarding the Motion <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013197917'>204</a> Motion for Relief from Judgment/Order Under Rule 9024 filed by Debtor Charlene Parks, <a href='https://ecf.ctb.uscourts.gov/doc1/040013200798'>205</a> Motion to Withdraw as Attorney filed by Debtor Charlene Parks)</font>. (Pettway, Christina)"
		 * , true));
		 */
		try {
			searchIndex();
//			another("Hearing Held for the reasons stated on the record. The Motion for something From Judgment/Order Under Rule 9024, ECF No. 204 is granted. by October 22, 2019. The Motion to Withdraw22tion <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013197917'>204</a> Motion for Relief from Judgment/Order Under Rule 9024 arks, <a href='https://ecf.ctb.uscourts.gov/doc1/040013200798'>205</a> Motion to Withdraw as Attorney filed by Debtor Charlene Parks)</font>. (Pettway, Christina)", "something");
//			another("Hearing Held for the reasons stated on the record. The Motion for Relief From Judgment/Order Under Rule 9024, ECF No. 204 is granted. A proposed order to be submitted by October 22, 2019. The Motion to Withdraw, ECF No. 205 is held until October 22, 2019 as Counsel to file something on docket regarding the Motion <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013197917'>204</a> Motion for Relief from Judgment/Order Under Rule 9024 filed by Debtor Charlene Parks, <a href='https://ecf.ctb.uscourts.gov/doc1/040013200798'>205</a> Motion to Withdraw as Attorney filed by Debtor Charlene Parks)</font>. (Pettway, Christina)", "something");
//			another("<b>ORDER DENYING MOTION FOR ORDER FOR EMERGENCY RELIEF.</b> The Motion for Order for Emergency Relief, ECF No. <a href='https://ecf.ctb.uscourts.gov/doc1/040013249190'>6</a>, is <b>DENIED</b>. Although the Debtor and her brother-in-law appeared at an emergency hearing held on September 24, 2019, they were not able to show any cause for the relief requested in the Motion and failed to inform the Court that an Order for In Rem Relief regarding the property known as 106B Comstock Hill Road, Norwalk, Connecticut, was entered in the Debtor&#039;s husband&#039;s case on September 18, 2018, Case No. 18-50975, which is evidence that the Debtor filed her Chapter 13 petition in bad faith. During the September 24th hearing, the Debtor&#039;s brother-in-law indicated that Attorney David Scalzi provided advice to the Debtor and/or her family in connection with the filing of this Chapter 13 case and counsel for Bank of New York as Trustee for the Certificateholders of CHL Mortgage Pass-Through Trust 2003-15 c/o Countrywide Home Loans, Inc. and the State Court Marshal testified and represented to the Court that Attorney David Scalzi informed them that the Debtor has filed a Chapter 13 petition on September 23, 2019 and that the State Court Ordered eviction was therefore stayed. At or before 4:00 p.m. on September 26, 2019, the Clerk&#039;s Office shall serve a copy of this Order on the Pro Se Debtor at the address listed on her petition and on Attorney David Scalzi via his CM/ECF email address and Attorney Scalzi will be the subject of a further Order imposing sanctions against him for his continued and repeated misconduct before this Court. <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013249190'>6</a>)</font>. Signed by Chief Judge Julie A. Manning on September 26, 2019. (Senteio, Renee)", "bad faith");
//			another("<b>ORDER DENYING MOTION FOR ORDER FOR EMERGENCY RELIEF.</b> The Motion for Order for Emergency Relief, ECF No. <a href='https://ecf.ctb.uscourts.gov/doc1/040013249190'>6</a>, is <b>DENIED</b>. Although the Debtor and her brother-in-law appeared at an emergency hearing held on September 24, 2019, they were not able to show any cause for the relief requested in the Motion and failed to inform the Court that an Order for In Rem Relief regarding the property known as 106B Comstock Hill Road, Norwalk, Connecticut, was entered in the Debtor&#039;s husband&#039;s case on September 18, 2018, Case No. 18-50975, which is evidence that the Debtor filed her Chapter 13 petition in bad faith. During the September 24th hearing, the Debtor&#039;s brother-in-law indicated that Attorney David Scalzi provided advice to the Debtor and/or her family in connection with the filing of this Chapter 13 case and counsel for Bank of New York as Trustee for the Certificateholders of CHL Mortgage Pass-Through Trust 2003-15 c/o Countrywide Home Loans, Inc. and the State Court Marshal testified and represented to the Court that Attorney David Scalzi informed them that the Debtor has filed a Chapter 13 petition on September 23, 2019 and that the State Court Ordered eviction was therefore stayed. At or before 4:00 p.m. on September 26, 2019, the Clerk&#039;s Office shall serve a copy of this Order on the Pro Se Debtor at the address listed on her petition and on Attorney David Scalzi via his CM/ECF email address and Attorney Scalzi will be the subject of a further Order imposing sanctions against him for his continued and repeated misconduct before this Court. <font color=blue>(RE: <a href='https://ecf.ctb.uscourts.gov/doc1/040013249190'>6</a>)</font>. Signed by Chief Judge Julie A. Manning on September 26, 2019. (Senteio, Renee)", "bad faith");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
	
	private static void another(String dtext, String cEmbeddedText) throws IOException, ParseException {
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
		
		
		Directory _Directory = FSDirectory.getDirectory ( "C:\\Tamil\\temp\\casb_fts" );
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
		int fragmentsSize = 5;
		int actualSize = (dtext.length()/100)+1;
		if(actualSize > fragmentsSize) {
			fragmentsSize = actualSize;
		}
		dtext = dktextHighlighter.getBestFragments(
                new StandardAnalyzer().tokenStream("docket-text", dktTextReader),
                dtext,
                fragmentsSize,
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
	
	private static void searchIndex()
            throws IOException,
            org.apache.lucene.queryParser.ParseException,
            java.text.ParseException,
            SQLException {
        int totalHits = 0;
        int limit = 0;
        int section = 0;
        int sections = 0;
        long time1 = System.currentTimeMillis();

//        context.put("resultsPerPage", cResultsPerPage);

        StandardAnalyzer _SA = new StandardAnalyzer();
        String cSearchInclude = "docket-text";
        String cEmbeddedDocketText = UNSELECTED;
        String cEmbeddedDocumentText = UNSELECTED;
        String cEmbeddedText = "Chapter";
        Date cStartDateDate = new SimpleDateFormat("MM/dd/yyyy").parse("06/12/2019");
        Date cEndDateDate = new SimpleDateFormat("MM/dd/yyyy").parse("06/12/2019");
//        Date cStartDateDate = null;
//        Date cEndDateDate = null;
//        String cDocumentType = "court";
        String cDocumentType = UNSELECTED;
        String cPtyName ="";
        String cAtyName = "";
//        String cCaseType = "bk";
        String cCaseType = UNSELECTED;
        String[] cPrid = {"13362181"};
//        String[] cPrid = {UNSELECTED};
        String[] cCaseOffice = {UNSELECTED};
        String[] cCaseChapter = {"7"};
        String[] cCaseId = {"428692"};
//        String[] cCaseId = {UNSELECTED};
        String[] case_group_id = {"0"};
        String[] entry_group_id = {"0", "8"};
        String[] document_group_id = {"0", "1", "8", "24"};
        int cResultsPerPage = 10;
        int cStartIndex = 0;
        int cEndIndex = 0;
        boolean highlightDocketText = true;
//        String[] case_group_id = {"0"};
        

        int index = 2;

        if (cSearchInclude.equals(BkFtsDocument.SEARCH_DOCKET_TEXT)) {
            index = 0;
        } else if (cSearchInclude.equals(BkFtsDocument.SEARCH_DOCUMENT)) {
            index = 1;
        }

        PerFieldAnalyzerWrapper _PFAW;
        QueryParser _QP;

        Query _EmbeddedDocketTextQuery = null;
        if (!cEmbeddedDocketText.equals(UNSELECTED)) {
            _PFAW = new PerFieldAnalyzerWrapper(new KeywordAnalyzer());
            _PFAW.addAnalyzer(FtsDocument.DOCKET_TEXT, _SA);
            _QP = new QueryParser(FtsDocument.DOCKET_TEXT, _PFAW);
            _EmbeddedDocketTextQuery = _QP.parse(cEmbeddedDocketText);
        }

        /*Query _EmbeddedDocumentTextQuery = null;
        if (!cEmbeddedDocumentText.equals(UNSELECTED)) {
            _PFAW = new PerFieldAnalyzerWrapper(new KeywordAnalyzer());
            _PFAW.addAnalyzer(FtsDocument.DOCUMENT_EMBEDDED_TEXT, _SA);
            _QP = new QueryParser(FtsDocument.DOCUMENT_EMBEDDED_TEXT, _PFAW);
            _EmbeddedDocumentTextQuery = _QP.parse(cEmbeddedDocumentText);
        }*/

        Query _EmbeddedTextQuery = null;
        if (!cEmbeddedText.equals(UNSELECTED)) {
            _PFAW = new PerFieldAnalyzerWrapper(new KeywordAnalyzer());

            // both docket text & document by default
            if (cSearchInclude.equals(BkFtsDocument.SEARCH_DOCKET_TEXT)) {
                _PFAW.addAnalyzer(FtsDocument.DOCKET_TEXT, _SA);
                _QP = new QueryParser(FtsDocument.DOCKET_TEXT, _PFAW);
            } else if (cSearchInclude.equals(BkFtsDocument.SEARCH_DOCUMENT)) {
                _PFAW.addAnalyzer(FtsDocument.DOCUMENT_EMBEDDED_TEXT, _SA);
                _QP = new QueryParser(FtsDocument.DOCUMENT_EMBEDDED_TEXT, _PFAW);
            } else {
                _PFAW.addAnalyzer(FtsDocument.DOCKET_TEXT, _SA);
                _PFAW.addAnalyzer(FtsDocument.DOCUMENT_EMBEDDED_TEXT, _SA);
                String[] textSource = { FtsDocument.DOCKET_TEXT, FtsDocument.DOCUMENT_EMBEDDED_TEXT };
                _QP = new MultiFieldQueryParser (textSource, _PFAW);
            }

            _EmbeddedTextQuery = _QP.parse(cEmbeddedText);
        }

        ConstantScoreRangeQuery _FiledDateQuery = null;
        if ((cStartDateDate != null) && (cEndDateDate != null)) {
            _FiledDateQuery = new ConstantScoreRangeQuery(
                    FtsDocument.DOCKET_ENTRY_FILED_DATE,
                    NmdDateTools.dateToStringDefaultTimeZone(cStartDateDate,
                    NmdDateTools.Resolution.DAY),
                    NmdDateTools.dateToStringDefaultTimeZone(cEndDateDate,
                    NmdDateTools.Resolution.DAY),
                    true,
                    true);
        }

        // document type
        TermQuery _docTypeQuery = new TermQuery(new Term(FtsDocument.DOCKET_ENTRY_TYPE, cDocumentType));

		/*
		 * _PFAW = new PerFieldAnalyzerWrapper(new KeywordAnalyzer());
		 * _PFAW.addAnalyzer(BkFtsDocument.PARTY_IN_CASE, _SA); _QP = new
		 * QueryParser(BkFtsDocument.PARTY_IN_CASE, _PFAW); Query _partyInCase =
		 * _QP.parse(cPtyName);
		 * 
		 * _PFAW = new PerFieldAnalyzerWrapper(new KeywordAnalyzer());
		 * _PFAW.addAnalyzer(BkFtsDocument.ATTORNEY_IN_CASE, _SA); _QP = new
		 * QueryParser(BkFtsDocument.ATTORNEY_IN_CASE, _PFAW); Query _attyInCase =
		 * _QP.parse(cAtyName);
		 */

        TermQuery _CaseType = new TermQuery(new Term(FtsDocument.CASE_TYPE, cCaseType));

        BooleanQuery _BooleanQuery = new BooleanQuery();

        if (!cEmbeddedText.equals(UNSELECTED)) {
            _BooleanQuery.add(_EmbeddedTextQuery, BooleanClause.Occur.MUST);
		} /*
			 * else { if (!cEmbeddedDocketText.equals(UNSELECTED) &&
			 * !cEmbeddedDocumentText.equals(UNSELECTED)) { BooleanQuery
			 * embeddedDocketDocumentTextbooleanQuery = new BooleanQuery(); if
			 * (cSearchInclude.equals(BkFtsDocument.SEARCH_BOTH)) {
			 * embeddedDocketDocumentTextbooleanQuery.add(_EmbeddedDocketTextQuery,
			 * BooleanClause.Occur.SHOULD);
			 * embeddedDocketDocumentTextbooleanQuery.add(_EmbeddedDocumentTextQuery,
			 * BooleanClause.Occur.SHOULD); } else {
			 * embeddedDocketDocumentTextbooleanQuery.add(_EmbeddedDocketTextQuery,
			 * BooleanClause.Occur.MUST);
			 * embeddedDocketDocumentTextbooleanQuery.add(_EmbeddedDocumentTextQuery,
			 * BooleanClause.Occur.MUST); }
			 * _BooleanQuery.add(embeddedDocketDocumentTextbooleanQuery,
			 * BooleanClause.Occur.MUST); } else if
			 * (!cEmbeddedDocketText.equals(UNSELECTED)) {
			 * _BooleanQuery.add(_EmbeddedDocketTextQuery, BooleanClause.Occur.MUST); } else
			 * if (!cEmbeddedDocumentText.equals(UNSELECTED)) {
			 * _BooleanQuery.add(_EmbeddedDocumentTextQuery, BooleanClause.Occur.MUST); } }
			 */

        if (_FiledDateQuery != null) {
            _BooleanQuery.add(_FiledDateQuery, BooleanClause.Occur.MUST);
        }

        if (!(cDocumentType.equals(UNSELECTED))) {
            _BooleanQuery.add(_docTypeQuery,
                    cDocumentType.equalsIgnoreCase("all")
                    ? BooleanClause.Occur.SHOULD
                    : BooleanClause.Occur.MUST);
        }

        if (!(cPrid[0].equals(UNSELECTED))) {
            BooleanQuery _pridQ = new BooleanQuery();
            _pridQ.setMinimumNumberShouldMatch(1);
            for (String _prid : cPrid) {
                _pridQ.add(
                        new TermQuery(
                        new Term(
                        "judge-prid",
                        _prid)),
                        BooleanClause.Occur.SHOULD);
            }

            if (cPrid.length > 0) {
                _BooleanQuery.add(_pridQ,
                        BooleanClause.Occur.MUST);
            }
        }

        if (!(cCaseType.equals(UNSELECTED))) {
            _BooleanQuery.add(_CaseType,
                    BooleanClause.Occur.MUST);
        }

		/*
		 * if (!(cPtyName.equals(UNSELECTED))) { _BooleanQuery.add(_partyInCase,
		 * BooleanClause.Occur.MUST); }
		 * 
		 * if (!(cAtyName.equals(UNSELECTED))) { _BooleanQuery.add(_attyInCase,
		 * BooleanClause.Occur.MUST); }
		 */

        //adding office for issue 27629
        if (!(cCaseOffice[0].equals(UNSELECTED))) {
            BooleanQuery _caseOffice = new BooleanQuery();
            _caseOffice.setMinimumNumberShouldMatch(1);
            for (String _CaseOffice : cCaseOffice) {
                _caseOffice.add(
                        new TermQuery(
                        new Term(
                        BkFtsDocument.CASE_OFFICE,
                        _CaseOffice)),
                        BooleanClause.Occur.SHOULD);
            }

            if (cCaseOffice.length > 0) {
                _BooleanQuery.add(_caseOffice, (cCaseOffice[0].equals(UNSELECTED))
                        ? BooleanClause.Occur.SHOULD
                        : BooleanClause.Occur.MUST);
            }
        }


        //adding chapter for issue 27629
        if (!(cCaseChapter[0].equals(UNSELECTED))) {
            BooleanQuery _caseChapter = new BooleanQuery();
            _caseChapter.setMinimumNumberShouldMatch(1);
            for (String _CaseChapter : cCaseChapter) {
                _caseChapter.add(
                        new TermQuery(
                        new Term(
                        BkFtsDocument.CASE_CHAPTER,
                        _CaseChapter)),
                        BooleanClause.Occur.SHOULD);
            }

            if (cCaseChapter.length > 0) {
                _BooleanQuery.add(_caseChapter, (cCaseChapter[0].equals(UNSELECTED))
                        ? BooleanClause.Occur.SHOULD
                        : BooleanClause.Occur.MUST);
            }
        }

        if (!(cCaseId[0].equals(UNSELECTED))) {
            BooleanQuery _caseId = new BooleanQuery();
            _caseId.setMinimumNumberShouldMatch(1);
            for (String _CaseId : cCaseId) {
                _caseId.add(
                        new TermQuery(
                        new Term(
                        BkFtsDocument.CASE_ID,
                        _CaseId)),
                        BooleanClause.Occur.SHOULD);
            }

            _BooleanQuery.add(_caseId,
                    BooleanClause.Occur.MUST);
        }

        BooleanQuery _caseGroupId = new BooleanQuery();
        _caseGroupId.setMinimumNumberShouldMatch(1);
        for (String _CaseGroupId : case_group_id) {
            _caseGroupId.add(
                    new TermQuery(
                    new Term(
                    BkFtsDocument.CASE_GROUP_ID,
                    _CaseGroupId)),
                    BooleanClause.Occur.SHOULD);
        }

        _BooleanQuery.add(_caseGroupId,
                BooleanClause.Occur.MUST);

        BooleanQuery _entryGroupId = new BooleanQuery();
        _entryGroupId.setMinimumNumberShouldMatch(1);
        for (String _EntryGroupId : entry_group_id) {
            _entryGroupId.add(
                    new TermQuery(
                    new Term(
                    BkFtsDocument.ENTRY_GROUP_ID,
                    _EntryGroupId)),
                    BooleanClause.Occur.SHOULD);
        }

        if (entry_group_id.length > 0) {
            _BooleanQuery.add(_entryGroupId, (entry_group_id[0].equals(UNSELECTED))
                    ? BooleanClause.Occur.SHOULD
                    : BooleanClause.Occur.MUST);
        }

        BooleanQuery _documentGroupId = new BooleanQuery();
        _documentGroupId.setMinimumNumberShouldMatch(1);
        for (String _DocumentGroupId : document_group_id) {
            _documentGroupId.add(
                    new TermQuery(
                    new Term(
                    BkFtsDocument.DOCUMENT_GROUP_ID,
                    _DocumentGroupId)),
                    BooleanClause.Occur.SHOULD);
        }

        _BooleanQuery.add(_documentGroupId,
                BooleanClause.Occur.MUST);

		/*
		 * getAuditLog().info( LogMessage.createMessage("Query: " +
		 * _BooleanQuery.toString()));
		 */

        ArrayList<BkFtsDocument> _AR = new ArrayList<BkFtsDocument>();

        Directory _Directory = FSDirectory.getDirectory( "C:\\Tamil\\temp\\cacb_fts");

        //add data structure
		/*
		 * HashMap judge_obj = (HashMap)
		 * getServletContext().getAttribute("judge_object"); if (judge_obj == null) {
		 * JudgesSql aj = new JudgesSql(getDataSource().getConnection()); judge_obj =
		 * aj.getJudgeHash(); judge_obj_requeried = true;
		 * getServletContext().setAttribute("judge_object", judge_obj); }
		 */
        boolean isSubQueryIncluded = false;
        try {
            IndexReader _IndexReader = IndexReader.open(_Directory);
            try {
                IndexSearcher _IndexSearcher = new IndexSearcher(_Directory);

                try {
                    //Hits _Hits = null;
                    //changed for issue 25321 pagination
                    Query que = null;
                    TopDocCollector collector = new TopDocCollector(2000);

                    if (cEmbeddedText.equals(UNSELECTED)
                            && cEmbeddedDocketText.equals(UNSELECTED)
                            && cEmbeddedDocumentText.equals(UNSELECTED)
                            && !isSubQueryIncluded) {
                        //_Hits = _IndexSearcher.search ( new MatchAllDocsQuery () );
                        //_IndexSearcher.search ( new MatchAllDocsQuery (),collector );
//                        que = new MatchAllDocsQuery();
                        _IndexSearcher.search(que, collector);
                    } else {
                        //_Hits = _IndexSearcher.search ( _BooleanQuery );
                        //_IndexSearcher.search ( _BooleanQuery,collector);
                        que = _BooleanQuery;
                        _IndexSearcher.search(que, collector);
                    }
                    //added for issue 25321 pagination
                    ScoreDoc[] hits = collector.topDocs().scoreDocs;
                    totalHits = hits.length;
                    //adding configuaral hits per page
                    //sections = totalHits/10;
                    sections = totalHits / cResultsPerPage;
                    if (sections > 0) {
                        limit = cResultsPerPage;
                        section = 1;
                        if (totalHits % cResultsPerPage != 0) {
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
                        section = cEndIndex / cResultsPerPage;
                    }
                    //Iterator _I = _Hits.iterator ();
                    //for ( int i = 0; ( i < 2000 ) && _I.hasNext (); i++ )
                    if (cEndIndex == hits.length && cEndIndex < 2000) {
                        //adding appellate 1.22 changes
                        if (totalHits % cResultsPerPage != 0) {
                            section++;
                        }
                    }

                    //Highlight
                    SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span class=\"highlight\">", "</span>");
                    Highlighter dktextHighlighter = null;
                    if ( highlightDocketText && !cEmbeddedText.equals ( UNSELECTED )
                                && (cSearchInclude.equals ( BkFtsDocument.SEARCH_BOTH ) || cSearchInclude.equals ( BkFtsDocument.SEARCH_DOCKET_TEXT) )) {
                        dktextHighlighter = new Highlighter(formatter, new QueryScorer(_EmbeddedTextQuery.rewrite(_IndexReader)));
                    } else if ( highlightDocketText && !cEmbeddedDocketText.equals ( UNSELECTED ) ) {
                        dktextHighlighter = new Highlighter(formatter, new QueryScorer(_EmbeddedDocketTextQuery.rewrite(_IndexReader)));
                    }
//                    Highlighter partyHighlighter = new Highlighter(formatter, new QueryScorer(_partyInCase.rewrite(_IndexReader)));
//                    Highlighter attorneyHighlighter = new Highlighter(formatter, new QueryScorer(_attyInCase.rewrite(_IndexReader)));

                    for (int i = lower; i < limit; i++) {
                        //Hit _H = ( Hit ) _I.next ();
                        int docId = hits[i].doc;
                        //Document _D = _H.getDocument ();
                        Document _D = _IndexSearcher.doc(docId);

						/*
						 * BkCaddDocument _DrsDocument = new BkCaddDocument();
						 * 
						 * _DrsDocument.setDocketEntryId(new Integer(_D.get(FtsDocument.CASE_ID)));
						 * _DrsDocument.setDocumentId(new Integer(_D.get(FtsDocument.DOCUMENT_ID)));
						 * _DrsDocument.setDocumentSeqno(new
						 * Integer(_D.get(FtsDocument.DOCUMENT_SEQNO))); _DrsDocument.setCaseId(new
						 * Integer(_D.get(FtsDocument.CASE_ID)));
						 */
                        //adding dls_id
                        try {
                            String _Dls_Id = _D.get(FtsDocument.DOCUMENT_DLS_ID);
                            if (_Dls_Id != null) {
//                                _DrsDocument.setDocumentDlsId(new Integer(_D.get(FtsDocument.DOCUMENT_DLS_ID)));
                            	System.out.println();
                                //getAuditLog ().info ( LogMessage.createMessage ( "1Document_dls_id --->  " + _Dls_Id ) );
                            } else {
                                //getAuditLog ().info ( LogMessage.createMessage ( "2Document_dls_id --->  " + _Dls_Id ) );
                            }
                        } catch (Exception e) {
//                            getAuditLog().info(LogMessage.createMessage("DLS ID null " + e));
                        }
						/*
						 * _DrsDocument.setCaseYear( new Integer(_D.get(FtsDocument.CASE_YEAR)));
						 * _DrsDocument.setCaseType( _D.get(FtsDocument.CASE_TYPE));
						 * 
						 * //added 7/6/09 _DrsDocument.setCaseNumber(new
						 * Integer(_D.get(FtsDocument.CASE_NUMBER))); _DrsDocument.setFullCaseNumber(new
						 * String(_D.get(FtsDocument.CASE_NUMBER_FULLY_FORMATED))); String case_title =
						 * _D.get(FtsDocument.CASE_SHORT_TITLE);
						 * 
						 * _DrsDocument.setCaseShortTitle(case_title);
						 * _DrsDocument.setDocumentScoreHits( //new Float ( _H.getScore () ) new
						 * Float(hits[i].score));
						 * 
						 * //adding dktenry filed date _DrsDocument.setDocketEntryFiledDate(
						 * NmdDateTools.stringToDateDefaultTimeZone(
						 * _D.get(FtsDocument.DOCKET_ENTRY_FILED_DATE)));
						 * 
						 * _DrsDocument.setDocumentRoot( _D.get(FtsDocument.DOCUMENT_ROOT));
						 * _DrsDocument.setDocketEntryType( _D.get(FtsDocument.DOCKET_ENTRY_TYPE));
						 * _DrsDocument.setDocketEntrySubType(
						 * _D.get(FtsDocument.DOCKET_ENTRY_SUB_TYPE));
						 */

                        String _DocketEntrySeqno = _D.get(FtsDocument.DOCKET_ENTRY_SEQNO);
						/*
						 * if (_DocketEntrySeqno != null) { _DrsDocument.setDocketEntrySeqno(new
						 * Integer(_DocketEntrySeqno)); }
						 */

                        //_DrsDocument.setDocketText ( getFilteredText ( _D.get ( FtsDocument.DOCKET_TEXT ), false ) );
                        String _caseid = _D.get(FtsDocument.DOCKET_ENTRY_ID);
                        String _deseqno = _D.get(FtsDocument.DOCKET_ENTRY_SEQNO);
                        String doc_root = (String) _D.get(FtsDocument.DOCUMENT_ROOT);
                        String docGraphics = "none";
                        String isEncrypted = (String) _D.get(FtsDocument.DOCUMENT_ENCRYPTED);

                        // safety!
                        if (isEncrypted == null) {
                            isEncrypted = "f";
                        }

						/*
						 * if (isEncrypted.equals("t")) { docGraphics = "doclock.gif";
						 * _DrsDocument.setDocumentEncrypted(FtsDocument.IndexTrueFalseUnknown.TRUE); }
						 * else if (isEncrypted.equals("f")) { docGraphics = "document.gif";
						 * _DrsDocument.setDocumentEncrypted(FtsDocument.IndexTrueFalseUnknown.FALSE); }
						 * else {
						 * _DrsDocument.setDocumentEncrypted(FtsDocument.IndexTrueFalseUnknown.UNKNOWN);
						 * }
						 */
                        //String dtext = getFilteredText ( _D.get ( FtsDocument.DOCKET_TEXT ),false );
                        String dtext = _D.get(FtsDocument.DOCKET_TEXT);

                        if ( highlightDocketText && (!cEmbeddedText.equals ( UNSELECTED )
                            && (cSearchInclude.equals ( BkFtsDocument.SEARCH_BOTH ) || cSearchInclude.equals ( BkFtsDocument.SEARCH_DOCKET_TEXT ) ))
                            || (highlightDocketText && !cEmbeddedDocketText.equals ( UNSELECTED )) )
                        {
                            StringReader dktTextReader = new StringReader((dtext));
                            dtext = dktextHighlighter.getBestFragments(
                                    new StandardAnalyzer().tokenStream(FtsDocument.DOCKET_TEXT, dktTextReader),
                                    dtext,
                                    5,
                                    "...");
                            dktTextReader.close();
                            if (dtext.isEmpty()) {
                                dtext = _D.get(FtsDocument.DOCKET_TEXT);
                            }
                        }

                        // we are now using org.apache.lucene.search.highlight.Highlighter
                        //fixing issue 30408 to not display naked text (as it is stored in index) but meta-cized
                        //text with unapproved tags taken out
                        StringUtil.Meta(dtext);
//                        _DrsDocument.setDocketText(StringUtil.Meta(dtext));

                        String _DocketEntryNumber = _D.get(FtsDocument.DOCKET_ENTRY_DOCUMENT_NUMBER);

                        /*if (_DocketEntryNumber != null) {
                            _DrsDocument.setDocketEntryDocumentNumber(
                                    new Integer(_DocketEntryNumber));
                        }
*/
//                        String[] _PridSA = _D.getValues(Judge.PRID);
/*
                        if (_PridSA != null) {
                            Collection<Judge> _JudgeC = new ArrayList<Judge>();


                            for (int j = 0; j < _PridSA.length; j++) {
                                Judge _Judge = new Judge();

                                _Judge.setPrid(_PridSA[j]);
                                //added 7/6/09 dem
                                String jud_full_name = (String) judge_obj.get(_PridSA[j]);
                                if (jud_full_name == null) {
                                    if (judge_obj_requeried) {
                                        jud_full_name = "not available";
                                        getAuditLog().info(LogMessage.createMessage("Judge full name is null" + jud_full_name));
                                    } else {

                                        JudgesSql aj = new JudgesSql(getDataSource().getConnection());
                                        judge_obj = aj.getJudgeHash();
                                        judge_obj_requeried = true;
                                        getServletContext().setAttribute("judge_object", judge_obj);
                                    }
                                }

                                _Judge.setCompleteName(jud_full_name);

                                _JudgeC.add(_Judge);
                            }

                            _DrsDocument.setAssociatedJudgeCollection(_JudgeC);

                        }*/
                        //STILL NEED TO ENABLE MULTIPLE OFFICE/CHAPTERS
                        // issue 27629
						/*
						 * String[] _caseChapter = _D.getValues(BkFtsDocument.CASE_CHAPTER); if
						 * (_caseChapter != null) { Collection<CaseChapter> _chapter = new
						 * ArrayList<CaseChapter>(); for (int j = 0; j < _caseChapter.length; j++) {
						 * CaseChapter this_chapter = new CaseChapter();
						 * this_chapter.setCaseChapter(_caseChapter[j]); _chapter.add(this_chapter); }
						 * _DrsDocument.setCaseChapterCollection(_chapter); } String[] _caseOffice =
						 * _D.getValues(BkFtsDocument.CASE_OFFICE); if (_caseOffice != null) {
						 * Collection<CaseOffice> _office = new ArrayList<CaseOffice>(); for (int j = 0;
						 * j < _caseOffice.length; j++) { CaseOffice this_office = new CaseOffice();
						 * this_office.setCaseOffice(_caseOffice[j]); _office.add(this_office); }
						 * _DrsDocument.setCaseOfficeCollection(_office); }
						 * 
						 * //added 7/6/09 dem String[] _ptyNames =
						 * _D.getValues(BkFtsDocument.PARTY_IN_CASE); if (_ptyNames != null) {
						 * Collection<Party> _pty = new ArrayList<Party>(); // for (int j =0 ;j <
						 * _ptyNames.length;j++){ // Party this_pty = new Party(); //
						 * this_pty.setLastName(_ptyNames[j]);
						 * 
						 * for (String _Pty : _ptyNames) { Party this_pty = new Party(); if
						 * (!cPtyName.equals(UNSELECTED)) { StringReader _PtyReader = new
						 * StringReader((_Pty)); _Pty = partyHighlighter.getBestFragments( new
						 * StandardAnalyzer().tokenStream(BkFtsDocument.PARTY_IN_CASE, _PtyReader),
						 * _Pty, 5, "..."); _PtyReader.close(); } this_pty.setLastName(_Pty);
						 * _pty.add(this_pty); }
						 * 
						 * _DrsDocument.setPartyInCaseCollection(_pty); }
						 * 
						 * String[] _atyNames = _D.getValues(BkFtsDocument.ATTORNEY_IN_CASE); if
						 * (_atyNames != null) { Collection<Attorney> _aty = new ArrayList<Attorney>();
						 * // for (int j =0 ;j < _atyNames.length;j++){ // Attorney this_aty = new
						 * Attorney(); // this_aty.setLastName(_atyNames[j]);
						 * 
						 * for (String _Aty : _atyNames) { Attorney this_aty = new Attorney(); if
						 * (!cAtyName.equals(UNSELECTED)) { StringReader _AtyReader = new
						 * StringReader((_Aty)); _Aty = attorneyHighlighter.getBestFragments( new
						 * StandardAnalyzer().tokenStream(BkFtsDocument.ATTORNEY_IN_CASE, _AtyReader),
						 * _Aty, 5, "..."); _AtyReader.close(); } this_aty.setLastName(_Aty);
						 * _aty.add(this_aty); } _DrsDocument.setAttorneyInCaseCollection(_aty); }
						 */
                        String[] _documentGroupIds = _D.getValues(BkFtsDocument.DOCUMENT_GROUP_ID);
                        String[] _entryGroupIds = _D.getValues(BkFtsDocument.ENTRY_GROUP_ID);
                        String[] _caseGroupIds = _D.getValues(BkFtsDocument.CASE_GROUP_ID);
                        //this is where access to the document is verified, if no access,
                        //the document is not added to the results to be returned
						/*
						 * if (verify_access_to_this_entry(case_group_id, _entryGroupIds) ||
						 * verify_access_to_this_entry(case_group_id, _caseGroupIds)) {
						 * _AR.add(_DrsDocument); //totalHits++; }
						 */
                    }
                } finally {
                    _IndexSearcher.close();
                }
            } finally {
                _IndexReader.close();
            }

			/*
			 * context.put("drsDocumentList", _AR); context.put("query",
			 * _BooleanQuery.toString()); //added for pagination context.put("fetchedHits",
			 * limit); context.put("hits", totalHits); context.put("page", section);
			 * context.put("pages", sections); context.put("history", cHistory);
			 * context.put("searchInclude", cSearchInclude); // context.put("cjudgeList",
			 * cjudgeList); context.put("cjudgeDisplay", cjudgeList); //
			 * writeJudgeList(context); context.put("caseTypeList", cCaseType); //
			 * writeCaseTypeList(context); writeDocTypeList(context); //27629
			 * context.put("caseChapter", cCaseChapterList); context.put("caseOffice",
			 * cCaseOfficeList); writeBKVars(context); //context.put ( "startIndex",
			 * cStartIndex ); //context.put ( "endIndex", cEndIndex ); long time_diff =
			 * (time1 - System.currentTimeMillis());
			 * getAuditLog().info(LogMessage.createMessage("Query took --->  " + new
			 * Long(time_diff).toString() + " millisecs "));
			 */
        } finally {
            //added for pagination
//            context.put("history", cHistory);
            _Directory.close();
        }
    }
    //added for pagination


}
