package com.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ProductCriteriaExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sessionFactory=HBUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		
		//Get all Products
		Criteria criteria =  session.createCriteria(Products.class);
		List<Products> proList = criteria.list();
		for(Products pro: proList) {
			System.out.println(pro.getProductname()+" "+pro.getQty()+" "+pro.getPrice());
		}

		//	select all records of product table whose price is greater than 500/-
			criteria = session.createCriteria(Products.class).add(Restrictions.gt("price", new Float(500.0)));
			List<Products> proList1 = criteria.list();
			System.out.println("Products whose price is greater than Rs.500/-");
			for(Products pro1:proList1) {
			System.out.println("Product Name : " + pro1.getProductname() + " ==> " + "Price : " + pro1.getPrice());	
			}
			
			
		//	select all records of product table whose qty is less than 10pcs.
			criteria = session.createCriteria(Products.class).add(Restrictions.lt("qty", new Integer(10)));
			List<Products> proList2 = criteria.list();
			System.out.println("Products whose quantity is less than 10pcs ");
			for(Products pro2:proList2) {
			System.out.println("Product Name : " + pro2.getProductname() + " ==> " + "Quantity : " + pro2.getQty());	
			}
			
	    // select number of products available
			System.out.println("Total number of Products available");
			Criteria td = session.createCriteria(Products.class);
			Long productCount = (Long) td.setProjection(Projections.rowCount()).uniqueResult();
			System.out.println("Number of products available: " + productCount);
			
			
		// select highest and lowest amount product
			Criteria d=session.createCriteria(Products.class);
			proList=d.setProjection(Projections.min("price")).list();
			System.out.println("Minimum price of all the products ");
			System.out.println("Min price : "+proList);
			proList1 =d.setProjection(Projections.max("price")).list();
			System.out.println("Maximum price of all the products ");
			System.out.println("Max price : "+proList1);

		// select sum of all available products price 
			Criteria d1=session.createCriteria(Products.class);
			proList2=d1.setProjection(Projections.sum("price")).list();
			System.out.println("Sum of the prices of all the products ");
			System.out.println("Sum of prices : "+proList2);
			tx.commit();
			
	    }
	}


