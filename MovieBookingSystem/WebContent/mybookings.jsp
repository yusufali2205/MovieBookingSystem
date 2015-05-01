<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="edu.ksu.cis.acad.dbutil.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="includes/remcaching.jsp" %>
<%    
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
    Database db = new Database();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>

        <script type="text/javascript" src="js/jcarousel.js"></script>
        <script type="text/javascript" src="js/jcarousel.responsive.js"></script>
        <script>
            jQuery.fn.center = function() {
                this.css("position", "fixed");
                this.css("top","0px");
                this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) +
                        $(window).scrollLeft()) + "px");
                return this;
            }
            $(function() {
                $("#message").center();
                $("#maincontent").load("mybookings");

                $("#mainWrapper").on("click", ".available", function() {
                    var id = $(this).attr("id");
                    $(this).removeClass("available");
                    $(this).toggleClass("selected");
                    if ($(this).attr("class") === "available") {
                        seats = $("#seats").val().replace(id + ",", "");
                    } else {
                        var seats = $("#seats").val();
                        seats += id + ",";
                    }
                    $("#seats").val(seats);
                });

                $("#mainWrapper").on("click", ".selected", function() {
                    var id = $(this).attr("id");
                    $(this).addClass("available");
                    $(this).toggleClass("selected");
                    if ($(this).attr("class") === "available") {
                        seats = $("#seats").val().replace(id + ",", "");
                    } else {
                        var seats = $("#seats").val();
                        seats += id + ",";
                    }
                    $("#seats").val(seats);
                });


                $("#mainWrapper").on("change", "#movie", function() {
                    $("#showtime").val("none");
                });
                $("#mainWrapper").on("change", "#theatre", function() {
                    $("#showtime").val("none");
                });
                $("#mainWrapper").on("submit", "#updatebooking", function(e) {
                    var tid = $("#theatre").val();
                    var mid = $("#movie").val();
                    var date = $("#datepicker").val();
                    var showtime = $("#showtime").val();
                    var seats = $("#seats").val();
                    var id = $("#itemid").val();
                    console.log(tid + " " + mid + " " + date + " " + showtime + " " + seats);
                    e.preventDefault();
                    $.ajax({
                        url: $(this).attr("action"),
                        type: "POST",
                        data: {"id": id, "tid": tid, "mid": mid, "date": date, "showtime": showtime, "seats": seats},
                        beforeSend: function() {
                            $("#message").show().html("Updating...").fadeOut(1000);
                        },
                        success: function(data) {
                            $("#message").html(data);
                            $("#loaded").slideUp();
                            $("#maincontent").load("mybookings");
                        }
                    });
                });
                //ajax call for getting ticket details
                $("#mainWrapper").on("click", "table td a.details", function(e) {
                    $("#loaded").hide();
                    e.preventDefault();
                    $.ajax({
                        url: $(this).attr("href"),
                        beforeSend: function() {
                            $("#message").show().html("Loading...").fadeOut(1000);
                        },
                        success: function(data) {
                            $("#loaded").html(data).slideDown("slow");
                        }
                    });
                });

                //ajax call for edit the ticket details
                $("#mainWrapper").on("click", "table td a.edit", function(e) {
                    $("#loaded").hide();
                    e.preventDefault();
                    $.ajax({
                        url: $(this).attr("href"),
                        beforeSend: function() {
                            $("#message").show().html("editing...").fadeOut(1000);
                        },
                        success: function(data) {
                            $("#loaded").html(data).slideDown("slow");
                        },
                        complete: function() {

                        }
                    });
                });

                //ajax call to cancel the ticket
                $("#mainWrapper").on("click", "table td a.cancel", function(e) {
                    var r = confirm("Are you sure you want to cancel your booking!");
                    if (r === true) {
                        e.preventDefault();
                        $.ajax({
                            url: $(this).attr("href"),
                            beforeSend: function() {
                                $("#message").show().html("cancelling...");
                            },
                            success: function(data) {
                                $("#message").html(data).fadeOut(2000);
                                $("#maincontent").load("mybookings");
                            }
                        });
                    } else {
                        return false;
                    }
                });
            });
        </script>
    </head>
    <body>
        <div id="mainWrapper">
            <div id="header">
                <div class="logo"></div>
                <div class="welcome">
                    <ul>
                        <li><strong>Welcome</strong>, <%
                            if (session.getAttribute("id") != null) {
                                out.print(db.getFullName((Integer) session.getAttribute("id")));
                            }
                            %>
                        </li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </div>
            </div>
            <div id="navigation">
                <ul>
                    <li><a href="." >HOME</a></li>
                    <li><a href="mybookings.jsp" class="last current">MY BOOKINGS</a></li>
                </ul>
            </div>
            <div id="loaded"></div> 
            <div id="maincontent">

            </div>

        </div>

        <div id="footerWrap">
            <div id="footer"></div>
        </div>
        <div id="message"></div>
    </body>
</html>
