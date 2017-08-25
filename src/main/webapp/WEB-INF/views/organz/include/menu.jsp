<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
			<h3>${list[0].orgnzDstnct } 수정하기</h3>
			<div class="col-lg-4 centering">
				<a id="gradBtn" class="btn btn-primary" href="${pageContext.servletContext.contextPath }/organz/list">대학원</a>
				<a id="uniBtn" class="btn btn-primary" href="${pageContext.servletContext.contextPath }/organz/unilist">대학교</a>
				<a id="deptBtn" class="btn btn-primary" href="${pageContext.servletContext.contextPath }/organz/deptlist">학과</a>
				<a id="labBtn" class="btn btn-primary" href="${pageContext.servletContext.contextPath }/organz/lablist">연구실</a>
				<c:if test="${list[0].orgnzDstnct=='대학원'||list[0].orgnzDstnct=='대학교'||list[0].orgnzDstnct=='학과'||list[0].orgnzDstnct=='연구실' }">
				<a id="insertBtn" class="btn btn-primary" href="${pageContext.servletContext.contextPath }/organz/insertform?type=${list[0].orgnzDstnct}">+</a>
				
				</c:if>
				
			</div>
