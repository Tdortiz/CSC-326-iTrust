package edu.ncsu.csc.itrust.model.ndcode;

import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.POJOValidator;
import edu.ncsu.csc.itrust.model.ValidationFormat;

public class NDCodeValidator extends POJOValidator<NDCode>{

    @Override
    public void validate(NDCode obj) throws FormValidationException {
        ErrorList errorList = new ErrorList();
        
        // NDCode
        errorList.addIfNotNull(checkFormat("NDCode", obj.getCode(), ValidationFormat.ND, false));

        // Description
        errorList.addIfNotNull(checkFormat("Description", obj.getDescription(), ValidationFormat.ND_CODE_DESCRIPTION, false));
        
        if (errorList.hasErrors()) {
            throw new FormValidationException(errorList);
        }
    }

}
