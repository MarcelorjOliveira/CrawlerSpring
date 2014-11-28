/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.model;

import br.com.crawlerspring.dao.JdbcDocumentDao;
//import br.com.crawlerspring.dao.JdbcUserDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author marcelooliveira
 */
public class Searcher {
    
    private JdbcDocumentDao documentDao = new JdbcDocumentDao();
    private StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
    private Directory index = new RAMDirectory();
    
    public void prepareSearch() throws IOException{
        
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
        
        IndexWriter writer = new IndexWriter(index, config);
      
        for (br.com.crawlerspring.model.Document document : documentDao.getDocuments() ){
            addDoc(writer, document.getTitle(), document.getContent());
        }
        
        writer.close();
    }
    
    private void addDoc(IndexWriter writer, String title, String content) throws IOException {
        org.apache.lucene.document.Document luceneDocument = new org.apache.lucene.document.Document();
        luceneDocument.add(new TextField("title", title, Field.Store.YES) );
        luceneDocument.add(new TextField("content", content, Field.Store.YES) );
        writer.addDocument(luceneDocument);
    }
    
    public List<br.com.crawlerspring.model.Document> parametrizeDocuments(String parameters) throws Exception{
        List<br.com.crawlerspring.model.Document> parametrizedDocuments = new ArrayList<br.com.crawlerspring.model.Document>();
        Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse(parameters);
        
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        
        for(int cont=0;cont<hits.length;++cont) {
            br.com.crawlerspring.model.Document document = new br.com.crawlerspring.model.Document();
            int docId = hits[cont].doc;
            org.apache.lucene.document.Document luceneDocument = searcher.doc(docId);
            document.setTitle(luceneDocument.get("title"));
            document.setContent(luceneDocument.get("content") );
            
            parametrizedDocuments.add(document);
        }
        return parametrizedDocuments;
    }
}