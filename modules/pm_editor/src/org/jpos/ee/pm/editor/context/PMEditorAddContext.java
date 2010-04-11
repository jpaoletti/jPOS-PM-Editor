package org.jpos.ee.pm.editor.context;

import java.io.File;

import org.jpos.ee.Constants;
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.EntityParser;
import org.jpos.ee.pm.core.OperationContext;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.struts.PMStrutsContext;

public class PMEditorAddContext implements OperationContext, Constants{

	public void preExecute(PMContext ctx) throws PMException {
		try {
			PMStrutsContext sctx = (PMStrutsContext) ctx;
			if(sctx.getEntityContainer().isSelectedNew()){
				Entity e = (Entity) ctx.getSelected().getInstance();
				String filename = "cfg/pm.default.operations.xml";
				File file = new File(filename);
				if(file.exists()){
					EntityParser ep = new EntityParser();
					e.setOperations(ep.parseOperationsFile(filename));
				}
			}
		} catch (Exception e) {
			throw new PMException(e);
		}
	}

	public void postExecute(PMContext ctx) throws PMException {}
}
