package org.jpos.ee.pm.editor.converter;

import org.jpos.ee.pm.converter.ConverterException;
import org.jpos.ee.pm.converter.IgnoreConvertionException;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.struts.converter.StrutsEditConverter;

public class ScopeConverter extends StrutsEditConverter{

	public String visualize(PMContext ctx) throws ConverterException{
		return "scope_converter.jsp?";
    }
    
	public Object build(PMContext ctx) throws ConverterException{
        throw new IgnoreConvertionException();
    }

}
