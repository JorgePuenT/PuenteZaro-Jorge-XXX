package lucene;

//import java.io.File;
//
//import javassist.bytecode.analysis.Analyzer;
//
//import org.jboss.jandex.IndexWriter;

public class SimpleFileIndexer {

	//	public void createIndex() throws Exception {
	//
	//		boolean create = true;
	//		File indexDirFile = new File(indexDir);
	//		if (indexDirFile.exists() && indexDirFile.isDirectory())
	//			create = false;
	//
	//		Directory dir = FSDirectory.open(indexDirFile);
	//		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
	//		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_43, analyzer);
	//
	//		if (create)
	//			// Create a new index in the directory, removing any
	//			// previously indexed documents:
	//			iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
	//
	//		IndexWriter writer = new IndexWriter(dir, iwc);
	//		writer.commit();
	//		writer.close(true);
	//	}

}