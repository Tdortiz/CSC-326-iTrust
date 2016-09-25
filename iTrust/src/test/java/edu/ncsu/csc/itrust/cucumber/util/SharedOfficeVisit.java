package edu.ncsu.csc.itrust.cucumber.util;

import edu.ncsu.csc.itrust.controller.officeVisit.OfficeVisitController;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;

public class SharedOfficeVisit {
	private OfficeVisit officeVisit;
	private OfficeVisitController officeVisitDAO;
	public SharedOfficeVisit() throws Exception {
		officeVisitDAO = new OfficeVisitController(((TestDAOFactory)TestDAOFactory.getTestInstance()).getDataSource());
	}
	public OfficeVisitController getOfficeVisitDAO() {
		return officeVisitDAO;
	}
	public OfficeVisit getOfficeVisit() {
		return officeVisit;
	}
	public void setOfficeVisit(OfficeVisit officeVisit) {
		this.officeVisit = officeVisit;
	}
}
