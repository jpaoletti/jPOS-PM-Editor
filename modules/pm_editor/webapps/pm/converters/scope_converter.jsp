<%@page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page import="org.jpos.ee.pm.core.*" import="org.jpos.ee.Constants" import="org.jpos.ee.pm.struts.PMEntitySupport" %>
<bean:define id="tmp_object" name = "entity_instance" type="java.lang.Object"/>
<bean:define id="value"  	value="<%= PMEntitySupport.getInstance().getAsString(tmp_object,request.getParameter("f"))%>"/>
<bean:define id="checkedi" value="${(value == 'item')?'checked':''}" />
<bean:define id="checkedg" value="${(value == 'general')?'checked':''}" />
<input type="radio" ${checkedi} value="item" id="f_${param.f}" name="f_${param.f}" /> Affects an instance <br/>
<input type="radio" ${checkedg} value="general" id="f_${param.f}" name="f_${param.f}" /> Affects all the entity