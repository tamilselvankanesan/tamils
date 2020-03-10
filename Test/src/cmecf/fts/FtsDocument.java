package cmecf.fts;

public class FtsDocument {
	public static final String INDEXED_DATE = "indexed-date";
    public static final String CONTENTS = "contents";
	//dmilste implementing district changes 6/29/09
    public static final String CASE_ID = "caseid";
    public static final String DOCKET_ENTRY_SEQNO = "entry-seqno";
    public static final String DOCKET_ENTRY_TYPE = "docket-entry-type";
    public static final String DOCKET_ENTRY_SUB_TYPE =
            "docket-entry-sub-type";
    public static final String DOCKET_ENTRY_TYPE_SUBTYPE =
            "docket-entry-type-subtype";
    public static final String DOCKET_ENTRY_DOCUMENT_NUMBER =
            "entry-doc-number";
    public static final String DOCKET_ENTRY_DATE_ENTERED =
            "docket-entry-date-entered";
    public static final String DOCKET_ENTRY_FILED_DATE =
            "docket-entry-filed-date";

	//new case and docket level restrictions
    public static final String DOCKET_ENTRY_CASE_RESTRICTION =
            "docket-entry-case-restriction";
    public static final String DOCKET_ENTRY_RESTRICTION =
            "docket-entry-restriction";
    public static final String DOCKET_ENTRY_TEXT_RECEIPT =
            "docket-entry-text-receipt";

    public static final String DOCKET_ENTRY_HAS_DOCKET_ENTRY =
            "docket-entry-has-docket-entry";
    public static final String DOCKET_ENTRY_HAS_DOCUMENT =
            "docket-entry-has-document";
    public static final String DOCKET_TEXT = "docket-text";
    public static final String CASE_YEAR = "case-year";
    public static final String CASE_TYPE = "case-type";
    public static final String CASE_NUMBER = "case-number";
    public static final String CASE_NUMBER_FULLY_FORMATED =
            "case-number-fully-formated";

    public static final String CASE_RESTRICTION = "case-restriction";
    // BK does not have case-wide-restrictions field unlike District
//    public static final String CASE_WIDE_DOCUMENT_RESTRICTION = "case-wide-document-restriction";

    public static final String CASE_SHORT_TITLE = "case-short-title";
    public static final String DOCKET_PART_COLLECTION =
            "docket-part-collection";
    public static final String DOCUMENT_ID = "document-id";
    public static final String DOCUMENT_SEQNO = "document-seqno";
    public static final String DOCUMENT_DOI = "document-doi";
    public static final String DOCUMENT_ROOT = "document-root";
    public static final String DOCUMENT_PATH = "document-path";
    public static final String DOCUMENT_UNIQUE_IDENTIFIER =
            "document-unique-identifer";
    public static final String DOCUMENT_PDF_FILE_NAME =
            "document-pdf-file-name";
    public static final String DOCUMENT_TEXT_FILE_NAME =
            "document-text-file-name";
    public static final String DOCUMENT_FULL_PDF_FILE_NAME =
            "document-full-pdf-file-name";
    public static final String DOCUMENT_FULL_TEXT_FILE_NAME =
            "document-full-text-file-name";
    public static final String DOCUMENT_EMBEDDED_TEXT =
            "document-text";
    public static final String DOCUMENT_HITS_SCORE = "document-score-hits";

    public static final String DOCUMENT_CASE_RESTRICTION =
            "document-case-restriction";
    public static final String DOCUMENT_ENTRY_RESTRICTION =
            "document-entry-restriction";
    public static final String DOCUMENT_RESTRICTION =
            "document-restriction";

    public static final String DOCUMENT_SIZE = "document-size";
    public static final String DOCUMENT_PAGES = "document-pages";
    public static final String DOCUMENT_TYPE = "document-type";
    public static final String DOCUMENT_ENCRYPTED = "document-encrypted";
    public static final String DOCUMENT_DECRYPTED_DOI =
            "document-decrypted-doi";
    public static final String DOCUMENT_SEARCHABLE = "document-searchable";
    public static final String DOCUMENT_OCRED = "document-ocred";
    // This indicates whether or not text extraction (via PDFBox)
    // completed normally without an exception being thrown
    public static final String DOCUMENT_OCR_TEXT = "ocr-text";
    public static final String DOCUMENT_EXTRACTION = "document-extraction";
    public static final String DOCUMENT_DLS_ID = "document-dls-id";    // hard-coded values
    // "searchable" means that the text within pdf is stored as text and
    // not as image(s) that represent text
    public static final String SEARCHABLE_TRUE = "t";
    public static final String SEARCHABLE_FALSE = "f";
    public static final String SEARCHABLE_UNKNOWN = "u";
    // "ocred" means that there was no "searchable" text and that
    // there are image(s) within the pdf
    public static final String OCRED_TRUE = "t";
    public static final String OCRED_FALSE = "f";
    public static final String OCRED_UNKNOWN = "u";
    public static final String DOCKET_ENTRY_ID = "docket-entry-id";
	//new values
	public static final String PARTY_NAME="party-name";
	public static final String ATTY_NAME="atty-name";
    public static final String GRAPHIC_URL = "graphic-url";
}
