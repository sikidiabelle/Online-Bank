<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@page import="simulatorBouse.CoursBourse"%>
<%@page import = "simulatorBouse.CoursBourseDAO"%>
<%@page import = "java.util.ArrayList"%>

      	<%

    	  	CoursBourseDAO bourse = new CoursBourseDAO();
      	ArrayList<CoursBourse> r = new ArrayList<CoursBourse>();
    	  	r = bourse.getCoursBourse();
    	  	ArrayList<ArrayList<String>> b = new ArrayList<ArrayList<String>>();
    	  	b=bourse.getCours();
    	  %>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        //var k = new google.visualization.arrayToDataTable([
        	var k = [
        	['Date/Heure',	'Indice'],
        	<% for(ArrayList<String> al : b){%>
        	[new Date(<%=al.get(0)%>),	<%=al.get(1)%>],
        	<%}%>
        ];
        	var t = new google.visualization.arrayToDataTable(k);
        
        

        var options = {
          title: 'CAC40 INDEXEURO',
          hAxis: {title: 'Date',  titleTextStyle: {color: '#333'}},
         
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(t, options);
      }
    </script>
  </head>
  <body>
    <div id="chart_div" style="width: 100%; height: 500px;"></div>
  </body>
  </html>