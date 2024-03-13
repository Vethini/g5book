package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.entity.BookDetails;

public class BookDAOImpl implements BookDAO{
	
	private Connection conn;
	
	public BookDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}


	public boolean addBooks(BookDetails b) {
		boolean f = false;
		try {
			String queryString = "insert into book_dtls(bookname, author, price, bookCategory, status, ref_id, email) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getPrice());
			ps.setString(4, b.getBookCategory());
			ps.setString(5,  b.getStatus());
			ps.setString(6, b.getRefId());
			ps.setString(7,  b.getEmail());
			
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}


	public List<BookDetails> getAllBooks() {
		
		List<BookDetails> list  = new ArrayList<BookDetails>();
		
		BookDetails bookDetails = null;
		
		try {
			String queryString = "select * from book_dtls";
			
			PreparedStatement pStatement = conn.prepareStatement(queryString);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}


	public BookDetails getBookById(int id) {
		BookDetails bookDetails = null;
		try {
			String queryString = "select * from book_dtls where bookId=?";
			PreparedStatement pStatement = conn.prepareStatement(queryString);
			pStatement.setInt(1, id);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookDetails;
	}

	public boolean updateEditBooks(BookDetails b) {
	    boolean f = false;
	    PreparedStatement pStatement = null;
	    try {
	        String queryString = "update book_dtls set bookName=?, author=?, price=?, status=? where bookId=?";
	        pStatement = conn.prepareStatement(queryString);
	        pStatement.setString(1, b.getBookName());
	        pStatement.setString(2, b.getAuthor());
	        pStatement.setString(3, b.getPrice());
	        pStatement.setString(4, b.getStatus());
	        pStatement.setInt(5, b.getBookId());

	        int i = pStatement.executeUpdate();

	        if (i == 1) {
	            f = true;
	        }

	    } catch (SQLException e) {
	        // Log the exception or handle it appropriately
	        e.printStackTrace();
	    } finally {
	        // Close the PreparedStatement in a finally block to ensure it gets closed
	        if (pStatement != null) {
	            try {
	                pStatement.close();
	            } catch (SQLException e) {
	                // Log the exception or handle it appropriately
	                e.printStackTrace();
	            }
	        }
	    }
	    return f;
	}



	public boolean deleteteBooks(int id) {
		
		boolean f = false;
		
		try {
			String queryString = "delete from book_dtls where bookId=?";
			PreparedStatement psPreparedStatement = conn.prepareStatement(queryString);
			psPreparedStatement.setInt(1, id);
			int i = psPreparedStatement.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}


	public List<BookDetails> getNewBookDetails() {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "New");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			int i = 1;
			while(rSet.next() && i<=4) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
				i++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}


	public List<BookDetails> getRecentBookDtls() {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails = null;
		try {
			String sqlString = "select * from book_dtls where status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			int i = 1;
			while(rSet.next() && i<=4) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
				i++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
public List<BookDetails> getOldBookDtls() {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Old");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			int i = 1;
			while(rSet.next() && i<=4) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
				i++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}


	public List<BookDetails> getAllRecentBooks() {

		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails = null;
		try {
			String sqlString = "select * from book_dtls where status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	public List<BookDetails> getAllNewBooks() {

		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "New");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	public List<BookDetails> getAllOldBooks() {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Old");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public List<BookDetails> getBookByOld(String email, String cate) {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails  =null;
		
		try {
			
			String sqlString = "select * from book_dtls where bookCategory=? and email=?";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, cate);
			ps.setString(2, email);
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}


	public boolean oldBookDelete(String email, String cate, int id) {
		
		boolean f = false;
		try {
			String sqlString = "delete from book_dtls where bookCategory=? and email=? and bookId=?";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, cate);
			ps.setString(2, email);
			ps.setInt(3, id);
			
			int i = ps.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		
		return f;
	}


	public List<BookDetails> getBookBySearch(String ch) {

		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails  =null;
		
		try {
//			System.out.println(ch);
			String sqlString = "select * from book_dtls where bookname like ? or author like ? or bookCategory like ? or ref_id like ? and status=?";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, "%"+ch+"%");
			ps.setString(2, "%"+ch+"%");
			ps.setString(3, "%"+ch+"%");
			ps.setString(4, "%"+ch+"%");
			ps.setString(5, "Active");
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				bookDetails = new BookDetails();
				bookDetails.setBookId(rSet.getInt(1));
				bookDetails.setBookName(rSet.getString(2));
				bookDetails.setAuthor(rSet.getString(3));
				bookDetails.setPrice(rSet.getString(4));
				bookDetails.setBookCategory(rSet.getString(5));
				bookDetails.setStatus(rSet.getString(6));
				bookDetails.setRefId(rSet.getString(7));
				bookDetails.setEmail(rSet.getString(8));
				list.add(bookDetails);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}


//	public BookDetails getBookById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//
//	public boolean updateEditBooks(BookDetails b) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//

	public List<BookDetails> getNewBookDtls() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}