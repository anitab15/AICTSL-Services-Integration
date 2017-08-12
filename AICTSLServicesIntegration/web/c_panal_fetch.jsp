<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%
    Object scannedMatchesObj = session.getAttribute("ALL_USER");
 
 ArrayList  sriList= (ArrayList)scannedMatchesObj;
  ArrayList al1=(ArrayList)sriList.get(0);
  ArrayList al2=(ArrayList)sriList.get(1);
  ArrayList al3=(ArrayList)sriList.get(2);
  
  String n1=al1.get(0).toString();
  String am1=al1.get(1).toString();
  String ex1=al1.get(2).toString();
 
  String n2=al2.get(0).toString();
  String am2=al2.get(2).toString();
  String ex2=al2.get(3).toString();
 
  String n3=al3.get(0).toString();
  String am3=al3.get(2).toString();
  String ex3=al3.get(3).toString();

  
  
  
%>
 
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Ibus</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/sb-admin.css" rel="stylesheet">
    <link href="css/plugins/morris.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Home</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
             
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="admin_img.jsp" width=25 height=30> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      
                        <li>
                            <a href="logout.jsp"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="index.jsp"><i class="fa fa-fw fa-dashboard"></i>Admin Panel</a>
                    </li>
                     <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Admin <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                                                    </ul>
                    </li>
                    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Ibus Control Panel <small>Admin is Mr.<%=session.getAttribute("uname")%></small>
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> Dashboard
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    
                                    <div class="col-xs-9 text-right">
                                        <div>New User!</div>
                                    </div>
                                   
                                </div>
                            </div>
                              <a href="new_user.jsp">
                                <div class="panel-footer">
                                    <span class="pull-left">Add User</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                              </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                   <div class="col-xs-9 text-right">
                                       <div> <%=n1%></div>
                                       <div> <%=n2%></div>
                                       <div> <%=n3%></div>
                                    </div>
                                </div>
                            </div>
                    
                            <div class="panel-footer">
                                <a href="#">
                                    <span class="pull-left"></span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </a>
                                </div>
                           </div>
                    </div>
                    
                </div>
                <!-- /.row -->
     </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
</body>

</html>
