package org.jpos.ee.pm.editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jpos.ee.pm.core.DataAccess;
import org.jpos.ee.pm.core.Entity;
import org.jpos.ee.pm.core.EntityFilter;
import org.jpos.ee.pm.core.EntityParser;
import org.jpos.ee.pm.core.PMContext;
import org.jpos.ee.pm.core.PMException;
import org.jpos.ee.pm.core.PMLogger;

public class DataAccessEntity implements DataAccess {

	
	private static final String EXTENSION = ".xml";
	private static final String OUTPUT = "output/";


	public void add(PMContext ctx, Object instance) throws PMException {
		try {
			Entity e = (Entity) instance;
			File file = new File(getFilename(e));
			file.createNewFile();
			EntityParser ep = new EntityParser();
			FileWriter fw = new FileWriter(file);
			ep.getXstream().toXML(instance, fw);
		} catch (Exception e1) {
			throw new PMException(e1);
		}
	}

	public Long count(PMContext ctx) throws PMException {
		File folder = new File(OUTPUT);
	    File[] listOfFiles = folder.listFiles();
	    return new Long(listOfFiles.length);
	}

	
	public EntityFilter createFilter(PMContext ctx) throws PMException {
		return new EntityFilter();
	}

	
	public void delete(PMContext ctx, Object object) throws PMException {
		Entity e = (Entity) object;
		ctx.getEntityContainer().getList().getContents().remove(e);
		File file = new File(getFilename(e));
	    file.delete();
	}

	
	public Object getItem(PMContext ctx, String property, String value)	throws PMException {
		EntityParser ep = new EntityParser();
		try {
			return ep.parseEntityFile(OUTPUT+value+EXTENSION);
		} catch (FileNotFoundException e) {
			throw new PMException(e);
		}
	}

	
	public List<?> list(PMContext ctx, EntityFilter filter, Integer from,Integer count) throws PMException {
		PMLogger.debug(this, "HOLAAAAAAAAA");
		List<Entity> result = new ArrayList<Entity>();
		EntityParser ep = new EntityParser();
		File folder = new File(OUTPUT);
	    File[] listOfFiles = folder.listFiles();

	    for (int i = from; i < from + count && i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  try {
				result.add(ep.parseEntityFile(OUTPUT+listOfFiles[i].getName()));
			} catch (FileNotFoundException e) {
				//Should not happen
			}
	      }
	    }
	    return result;
	}

	
	public Object refresh(PMContext ctx, Object o) throws PMException {
		Entity e = (Entity) o;
		return getItem(ctx, "id", e.getId());
	}

	
	public void update(PMContext ctx, Object instance) throws PMException {
		try {
			Entity e = (Entity) instance;
			File file = new File(getFilename(e));
			EntityParser ep = new EntityParser();
			FileWriter fw = new FileWriter(file);
			ep.getXstream().toXML(instance, fw);
		} catch (Exception e) {
			throw new PMException(e);
		}
	}

	private String getFilename(Entity e) {
		StringBuilder sb = new StringBuilder();
		sb.append(OUTPUT);
		sb.append(e.getId());
		sb.append(EXTENSION);
		return sb.toString();
	}
}
