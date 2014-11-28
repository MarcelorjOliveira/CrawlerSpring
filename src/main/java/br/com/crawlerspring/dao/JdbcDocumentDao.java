/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crawlerspring.dao;

import br.com.crawlerspring.connection.ConnectionFactory;
import br.com.crawlerspring.model.Document;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcelooliveira
 */
public class JdbcDocumentDao {
    
    private Connection connection;
    
    public JdbcDocumentDao(){
        	try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
    }
    
    public void createDocument(Document document) {
   		
		if(document == null) {
			throw new IllegalArgumentException("document does not exist");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("insert into document " +
                                "(id_document, title, content, author, date, link, source) " +
                                " values (?, ?, ?, ?, ?, ?, ?)" );
			stmt.setString(1, document.getId());
			stmt.setString(2, document.getTitle());
                        stmt.setString(3, document.getContent());
                        stmt.setString(4, document.getAuthor());
                        stmt.setDate(5, document.getDate());
                        stmt.setString(6, document.getLink());
                        stmt.setString(7, document.getSource());
                        
                        stmt.execute();
                        stmt.close();
                        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    public void emptyDocuments() {
   		
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("truncate document" );
                        stmt.execute();
                        stmt.close();
                        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    public List<Document> getDocuments(){
        List<Document> documents = new ArrayList<Document>();
        try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from document");
                        ResultSet rs = stmt.executeQuery();
                        
                        while(rs.next()) {
                            Document document = new Document();
                            document.setId(rs.getString("id_document"));
                            document.setDate(rs.getDate("date"));
                            document.setLink(rs.getString("link"));
                            document.setSource(rs.getString("source"));
                            document.setAuthor(rs.getString("author"));
                            document.setTitle(rs.getString("title"));
                            document.setContent(rs.getString("content"));
                            
                            documents.add(document);
                        }
                        rs.close();
                        stmt.close();
                        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
       return documents;
    }
    
}
