package edu.ncsu.csc.itrust.cucumber.util;

import edu.ncsu.csc.itrust.controller.officeVisit.OfficeVisitController;
import edu.ncsu.csc.itrust.model.officeVisit.OfficeVisit;
import edu.ncsu.csc.itrust.unit.testutils.TestDAOFactory;

public class SharedOfficeVisit {
	private OfficeVisit ov;
	private OfficeVisitController ovc;
	public SharedOfficeVisit() throws Exception {
		ovc = new OfficeVisitController(((TestDAOFactory)TestDAOFactory.getTestInstance()).getDataSource());
	}
	public OfficeVisitController getOfficeVisitController() {
		return ovc;
	}
	public OfficeVisit getOfficeVisit() {
		return ov;
	}
	public void setOfficeVisit(OfficeVisit officeVisit) {
		this.ov = officeVisit;
	}
	public void add() {
		ovc.add(this.ov);
	}
}
