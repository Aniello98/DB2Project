package it.polimi.db2.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import it.polimi.db2.views.Alert;
import it.polimi.db2.views.AverageOptionalPerPackage;
import it.polimi.db2.views.PurchasesPerPackage;
import it.polimi.db2.views.PurchasesPerPackageAndPeriod;
import it.polimi.db2.views.SalesPerProduct;
import it.polimi.db2.views.SuspendedOrders;
import it.polimi.db2.views.ValueSalesPerPackage;

@Stateless
public class ReportService {
	@PersistenceContext(unitName = "ProjectService")
	private EntityManager em;
	
	public ReportService() {
		
	}
	
	public List<PurchasesPerPackage> getPurchasesPerPackage() {
		return em.createNamedQuery("PurchasesPerPackage.findAll", PurchasesPerPackage.class).getResultList();
	}
	
	public List<PurchasesPerPackageAndPeriod> getPurchasesPerPackageAndPeriod() {
		List<PurchasesPerPackageAndPeriod> list = new ArrayList<PurchasesPerPackageAndPeriod>();
		list= em.createNamedQuery("PurchasesPerPackageAndPeriod.findAll", PurchasesPerPackageAndPeriod.class).getResultList();
		for(PurchasesPerPackageAndPeriod p : list) {
			System.out.println(p.getPeriodId() + " " + p.getPackageId());
		}
		return list;
	}
	
	public List<AverageOptionalPerPackage> getAverageOptionalPerPackage(){
		return em.createNamedQuery("AverageOptionalPerPackage.findAll", AverageOptionalPerPackage.class).getResultList();
	}
	
	public List<SalesPerProduct> getBestOptionalProductSeller(){
		return em.createNamedQuery("SalesPerProduct.findBestSeller", SalesPerProduct.class).setMaxResults(1).getResultList();
	}
	
	public List<Alert> getAlert(){
		return em.createNamedQuery("Alert.findAll", Alert.class).getResultList();
	}
	
	public List<ValueSalesPerPackage> getValueSalesPerPackage(){
		return em.createNamedQuery("ValueSalesPerPackage.findAll", ValueSalesPerPackage.class).getResultList();
	}
	
	public List<SuspendedOrders> getSuspendedOrders(){
		return em.createNamedQuery("SuspendedOrders.findAll", SuspendedOrders.class).getResultList();
	}
}
