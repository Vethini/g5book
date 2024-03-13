package com.DAO;

import java.util.List;

import com.entity.BookDetails;

public interface BookDAO {
	public boolean addBooks(BookDetails b);
	
	public List<BookDetails> getAllBooks();
	
	public BookDetails getBookById(int id);
	
	public boolean updateEditBooks(BookDetails b);
	
	public boolean deleteteBooks(int id);
	
	public List<BookDetails> getNewBookDtls();
	
	public List<BookDetails> getRecentBookDtls();
	
	public List<BookDetails> getOldBookDtls();
	
	public List<BookDetails> getAllRecentBooks();
	
	public List<BookDetails> getAllNewBooks();
	
	public List<BookDetails> getAllOldBooks();
	
	public List<BookDetails> getBookByOld(String email, String cate);
	
	public boolean oldBookDelete(String email, String cate, int id);
	
	public List<BookDetails> getBookBySearch(String ch);
}
