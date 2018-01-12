package com.example.ac.project_abel;

/**
 * Created by Wise on 10/26/2017.
 */

public class Globals {
    protected static final String theme_lawrencium = "lawrencium";
    protected static final String materials_url = "http://www.unilus.ac.zm/Students/Materials.aspx";
    protected static final String registration_url = "http://www.unilus.ac.zm/Students/RegistrationForm.aspx";
    protected static final String lectscon_url = "http://www.unilus.ac.zm/Students/LecturerContact.aspx";
    protected static final String viewca_url = "http://www.unilus.ac.zm/Students/ViewCA.aspx";
    protected static final String viewfinal_url = "http://www.unilus.ac.zm/Students/ViewResults.aspx";
    protected static final String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\"><head id=\"Head1\"><title>\n" +
            "\tUNIVERSITY OF LUSAKA\n" +
            "</title><link rel=\"icon\" type=\"image/ico\" href=\"../Images/favicon.ico\"><link href=\"../Styles/SiteV1.css\" rel=\"stylesheet\" type=\"text/css\"><link href=\"../Styles/Print.css\" rel=\"stylesheet\" type=\"text/css\" media=\"print\">\n" +
            "    <script type=\"text/javascript\" src=\"Scripts/jquery-1.4.1.min.js\"></script>\n" +
            "    <script type=\"text/javascript\" src=\"../ckeditor.js\"></script>\n" +
            "    <script type=\"text/javascript\">\n" +
            "        function slideShow() {\n" +
            "            var $active = $('#s_show IMG.active');\n" +
            "            if ($active.length == 0) $active = $('#s_show IMG:last');\n" +
            "            var $next = $active.next().length ? $active.next()\n" +
            "         : $('#s_show IMG:first');\n" +
            "            $active.addClass('last-active');\n" +
            "            $next.css({ opacity: 0.0 })\n" +
            "         .addClass('active')\n" +
            "         .animate({ opacity: 1.0 }, 1000, function () {\n" +
            "             $active.removeClass('active last-active');\n" +
            "         });\n" +
            "        }\n" +
            "\n" +
            "        $(function () {\n" +
            "            setInterval(\"slideShow()\", 10000);\n" +
            "        });\n" +
            "</script>\n" +
            "\n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "    <script type=\"text/javascript\">\n" +
            "\n" +
            "        function flasher() {\n" +
            "\n" +
            "            if (document.getElementById(\"flashMe\")) {\n" +
            "\n" +
            "                var d = document.getElementById(\"flashMe\");\n" +
            "\n" +
            "                d.style.color = (d.style.color == 'red' ? 'green' : 'red');\n" +
            "\n" +
            "                setTimeout('flasher()', 900);\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "   </script>\n" +
            "\n" +
            "<script type=\"text/javascript\">\n" +
            "       function CallPrint(strid) {\n" +
            "           var prtContent = document.getElementById(strid);\n" +
            "           var WinPrint = window.open('', '', 'letf=0,top=0,width=850,height=800,toolbar=0,scrollbars=1,status=0');\n" +
            "           WinPrint.document.write('<html><head><title>Popup</title>')\n" +
            "           WinPrint.document.write('<style type=\"text/css\"> body {color:#000000; font-size:1em; font-family: Helvetica Neue,Helvetica,Arial,sans-serif;}#menu{display:none;}#menuholder{display:none; height: 0;top: 0px left: 0px;} #printMenu {display:none;}a{ color:#000000;text-decoration: none;}</style>');\n" +
            "           WinPrint.document.write('</head><body>');\n" +
            "           WinPrint.document.write('`</body></html>');\n" +
            "           WinPrint.document.write(prtContent.innerHTML);\n" +
            "           WinPrint.document.close();\n" +
            "           WinPrint.focus();\n" +
            "           WinPrint.print();\n" +
            "       }\n" +
            "</script></head>\n" +
            "   \n" +
            "\n" +
            "<body onload=\"flasher()\">\n" +
            "     <form method=\"post\" action=\"./LecturerContact.aspx\" id=\"Form1\">\n" +
            "<div class=\"aspNetHidden\">\n" +
            "<input type=\"hidden\" name=\"__VIEWSTATE\" id=\"__VIEWSTATE\" value=\"/wEPDwUKMTAyMTMzODg1Ng9kFgJmD2QWAmYPZBYCAgMPZBYCAlkPZBYCAiEPZBYCAgEPFgIeBFRleHQFxAc8dGFibGUgc3R5bGU9J3dpZHRoOiAxMDAlJz4NCjx0cj4NCjx0ZD48Yj5Db3Vyc2U8L2I+PC90ZD4NCjx0ZD48Yj5OYW1lPC9iPjwvdGQ+DQo8dGQ+PGI+Q2VsbDwvYj48L3RkPg0KPHRkPjxiPkVtYWlsPC9iPjwvdGQ+DQo8L3RyPg0KPHRyIGNsYXNzPSdncmRFdmVuUm93Jz4NCjx0ZCBzdHlsZT0nd2lkdGg6IDglJz5FQ0YxMTA8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPk1yLiBUZWRkeSAgRnVueWluYSA8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPjA5NzgyNjY5OTE8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPmZ1bnlpbmF0a0B1bmlsdXMuYWMuem08L3RkPg0KPC90cj4NCjx0ciBjbGFzcz0nZ3JkT2RkUm93Jz4NCjx0ZCBzdHlsZT0nd2lkdGg6IDglJz5IUk0xMDA8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPk1yLiBKRUZGUkVZIE1VTEVZQTwvdGQ+DQo8dGQgc3R5bGU9J3dpZHRoOiA4JSc+OTc3ODA3NzkyPC90ZD4NCjx0ZCBzdHlsZT0nd2lkdGg6IDglJz5tdWxleWFqZWZmcmV5QGdtYWlsLmNvbTwvdGQ+DQo8L3RyPg0KPHRyIGNsYXNzPSdncmRFdmVuUm93Jz4NCjx0ZCBzdHlsZT0nd2lkdGg6IDglJz5BRklOMTAyPC90ZD4NCjx0ZCBzdHlsZT0nd2lkdGg6IDglJz5Nci4gSmFja3NvbiAgU2lzaHVtYmE8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPjA5Njc4MjkyMTk8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPnNpc2h1bWJhY2pAeWFob28uY29tPC90ZD4NCjwvdHI+DQo8dHIgY2xhc3M9J2dyZE9kZFJvdyc+DQo8dGQgc3R5bGU9J3dpZHRoOiA4JSc+RUNGMTEwPC90ZD4NCjx0ZCBzdHlsZT0nd2lkdGg6IDglJz5Nci4gSWJyYWhpbSBLYW1hcmE8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPjA5Nzc2MDc2OTQ8L3RkPg0KPHRkIHN0eWxlPSd3aWR0aDogOCUnPmlicmFoaW1rYW1hcmE5MzBAZ21haWwuY29tPC90ZD4NCjwvdHI+DQo8L3RhYmxlPg0KZGRMM0050dTqlTITBiB2ucHHI4riYyJyycMyqWHaHOB1OQ==\">\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"aspNetHidden\">\n" +
            "\n" +
            "\t<input type=\"hidden\" name=\"__VIEWSTATEGENERATOR\" id=\"__VIEWSTATEGENERATOR\" value=\"70D7E4F1\">\n" +
            "</div>\n" +
            "    \n" +
            "    <div id=\"Div1\" class=\"page\">\n" +
            " <div class=\"clear hideSkiplink\">\n" +
            "<a href=\"StudentPortal.aspx\" id=\"A14\">Students' Portal</a> | <a href=\"ChangePassword.aspx\" id=\"A33\">Change Password</a> | <a href=\"../Logout.aspx\" id=\"A15\">Log Out</a>         \n" +
            "        \n" +
            "              \n" +
            "            </div>\n" +
            "        <div id=\"Div2\" class=\"header\">\n" +
            "        \n" +
            "            <div class=\"title2\">\n" +
            "                    <img src=\"../Images/newyear.png\" id=\"A23\" alt=\"Unilus logo\" style=\"text-align: center\">                \n" +
            "               \n" +
            "            </div>            \n" +
            "   \n" +
            "</div>  \n" +
            "        </div>\n" +
            "        \n" +
            "\n" +
            "<div id=\"menuholder\">\n" +
            "\n" +
            "<div id=\"menu\">       \n" +
            "<ul>\n" +
            "    \n" +
            "    <li><a href=\"../Default.aspx\" id=\"A2\" class=\"drop\">Home</a><!-- Begin Home Item -->\n" +
            "    \n" +
            "        <div class=\"dropdown_1columns\"><!-- Begin 2 columns container -->\n" +
            "    \n" +
            "            <div class=\"col_1\">\n" +
            "                <h2>Welcome to UNILUS</h2>\n" +
            "\n" +
            "                <h1>The Vice Chancellor</h1>\n" +
            "             <h>Prof. Pinalo Chifwanakeni</h><br>\n" +
            "            <img src=\"../StaffImages/VC.jpg\" id=\"logo2\" height=\"100\" alt=\"The Vice Chancellor\" align=\"left\" style=\"margin-right:4px\">\n" +
            "           \n" +
            "             <p>Passion for Quality Education: Our Driving Force</p>\n" +
            "            </div>\n" +
            "    \n" +
            "            <div class=\"col_2\">\n" +
            "                <h2>Apply for admission :</h2>\n" +
            "\n" +
            "                <a href=\"../Applications/Apply.aspx\" id=\"app1\">Apply online NOW</a>\n" +
            "\n" +
            "                <h2>Application forms</h2>\n" +
            "                <a href=\"../Documents/UNILUS%20Undergraduate%20Application%20Form.pdf\" id=\"uA4\" target=\"_blank\">Undergraduate Application form</a> \n" +
            "                 <br><a href=\"../Documents/ACCA%20Application%20Form.pdf\" id=\"Acca\" target=\"_blank\"> ACCA Application form</a>\n" +
            "                 <br><a href=\"../Documents/UNILUS%20Postgraduate%20Application%20Form.pdf\" id=\"pA6\" target=\"_blank\">Postgraduate Application form</a>\n" +
            "               \n" +
            "            </div>\n" +
            "                \n" +
            "            <div class=\"col_3\">\n" +
            "                <h2>Fees Payments</h2>             \n" +
            "                <a href=\"../BankDetails.aspx\" id=\"A21\">Bank Details</a>          \n" +
            "                <br>  <a href=\"../Fees.aspx\" id=\"A22\">Fees</a>\n" +
            "           <h2>FAQs</h2>\n" +
            "           <a href=\"../FAQ.aspx\" id=\"A18\">Frequently Asked Questions</a> \n" +
            "            \n" +
            "                \n" +
            "            </div>\n" +
            "          \n" +
            "        </div><!-- End 2 columns container -->\n" +
            "    \n" +
            "    </li><!-- End Home Item -->\n" +
            "\n" +
            "     <li><a href=\"../About.aspx\" id=\"A17\" class=\"drop\">The University</a>\n" +
            "    <div class=\"dropdown_2columns\"><!-- Begin The University -->\n" +
            "    \n" +
            "            <div class=\"col_1\">\n" +
            "                <h2>About UNILUS</h2>\n" +
            "                <a href=\"../About.aspx\" id=\"A11\">About us</a>\n" +
            "               <br><a href=\"../News.aspx\" id=\"A3\">News &amp; Events</a>\n" +
            "               <br><a href=\"../GradingSystem.aspx\" id=\"A8\">Grading System</a>\n" +
            "               <br><a href=\"../Scholars.aspx\" id=\"A9\">Senior Scholars</a>              \n" +
            "               <br><a href=\"../Schools.aspx\" id=\"A20\">Schools</a> \n" +
            "            </div>\n" +
            "    \n" +
            "            <div class=\"col_2\">\n" +
            "            <br><a href=\"../Research.aspx\" id=\"A7\">Research &amp; Business</a>\n" +
            "           <br><a href=\"../Documents/Graduation%20Brochure.pdf\" id=\"gb4\" target=\"_blank\">Graduation Brochure</a>\n" +
            "           <br><a href=\"../Documents/Handbook.pdf\" id=\"uh5\" target=\"_blank\">University Handbook</a>\n" +
            "            <br><a href=\"../BSPHHealthProfessions.aspx\" id=\"b6\" target=\"_blank\">HPCZ Guidelines</a>\n" +
            "                  \n" +
            "                \n" +
            "            <h2>FAQs</h2>\n" +
            "           <a href=\"../FAQ.aspx\" id=\"A24\">Frequently Asked Questions</a>\n" +
            "                 </div>\n" +
            "            \n" +
            "            <div class=\"col_3\">\n" +
            "             \n" +
            "           <h2>Fees &amp; Payments</h2>             \n" +
            "                <a href=\"../BankDetails.aspx\" id=\"b1\">Bank Details</a>          \n" +
            "                <br><br>  <a href=\"../Fees.aspx\" id=\"f2\">Fees</a>\n" +
            "              \n" +
            "            \n" +
            "            </div> \n" +
            "       \n" +
            "          \n" +
            "        </div></li><!-- End The University -->\n" +
            "    <li><a href=\"../Schools.aspx\" id=\"A19\" class=\"drop\">Study at UNILUS</a><!-- Begin Study Item -->\n" +
            "    \n" +
            "        <div class=\"dropdown_3columns\"><!-- Begin 2 columns container -->\n" +
            "           <div class=\"col_1\">\n" +
            "              <h2>Programmes Offered</h2>\n" +
            "             <a href=\"SOBM.aspx\">School of Business and Management</a>\n" +
            "             <br><a href=\"SOE.aspx\">School of Education, Social Sciences and Technology</a>\n" +
            "             <br><a href=\"SOHS.aspx\">School of Health Sciences</a>\n" +
            "             <br><a href=\"SOL.aspx\">School of Law</a>         \n" +
            "             <br><a href=\"SOPS.aspx\">School of Postgraduate Studies</a>\n" +
            "           <h2>FAQs</h2>\n" +
            "           <a href=\"../FAQ.aspx\" id=\"f1\">Frequently Asked Questions</a> \n" +
            "            </div>\n" +
            "               \n" +
            "            \n" +
            "            <div class=\"col_2\">\n" +
            "                <h2>Successful Applicants 2018 January Intake</h2>\n" +
            "                <br><a href=\"../Lecturer/PostgradSuccessfulApplicants.aspx\" id=\"p1\">Postgraduates</a>\n" +
            "               <br><a href=\"../Lecturer/UndergradSuccessfulApplicants.aspx\" id=\"u1\">Undergraduates</a>\n" +
            "                 \n" +
            "                <h2>Fees &amp; Payments</h2>             \n" +
            "                <a href=\"../BankDetails.aspx\" id=\"A219\">Bank Details</a>          \n" +
            "                <br>  <a href=\"../Fees.aspx\" id=\"A30\">Fees</a>\n" +
            "                <br> <a href=\"../StudentsAffairs.aspx\" id=\"Ae26\">Student Affairs</a>\n" +
            "             \n" +
            "             \n" +
            "            </div>\n" +
            "            \n" +
            "            <div class=\"col_3\">\n" +
            "                <h2>Application</h2>        \n" +
            "                <a href=\"../Applications/Apply.aspx\" id=\"app2\">Online Application</a>\n" +
            "               <br><a href=\"../HowToApply.aspx\" id=\"A85\">How to apply</a>\n" +
            "                <h2>Application forms</h2>\n" +
            "                 <a href=\"../Documents/UNILUS%20Undergraduate%20Application%20Form.pdf\" id=\"Au24\" target=\"_blank\">Undergraduate Application form</a> \n" +
            "                 <br><a href=\"../Documents/ACCA%20Application%20Form.pdf\" id=\"Aa25\" target=\"_blank\"> ACCA Application form</a>\n" +
            "                 <br><a href=\"../Documents/UNILUS%20Postgraduate%20Application%20Form.pdf\" id=\"Ap25\" target=\"_blank\">Postgraduate Application form</a>\n" +
            "               \n" +
            "\n" +
            "            </div> \n" +
            "\n" +
            "          \n" +
            "        </div><!-- End 2 columns container -->\n" +
            "    \n" +
            "    </li><!-- End Study Item -->\n" +
            "    <li><a href=\"../Schools.aspx\" id=\"A16\" class=\"drop\">Programmes Offered</a><!-- Begin Programmes Item -->\n" +
            "    \n" +
            "        <div class=\"dropdown_4columns\"><!-- Begin 2 columns container -->\n" +
            "    \n" +
            "            <div class=\"col_1\">\n" +
            "                <h2>SCHOOLS</h2>\n" +
            "               \n" +
            "            <a href=\"SOBM.aspx\">School of Business and Management</a>\n" +
            "           <br> <a href=\"SOE.aspx\">School of Education, Social Sciences and Technology</a>\n" +
            "           <br> <a href=\"SOHS.aspx\">School of Health Sciences</a>\n" +
            "           <br><a href=\"SOL.aspx\">School of Law</a>       \n" +
            "            <br><a href=\"SOPS.aspx\">School of Postgraduate Studies</a>\n" +
            "        \n" +
            "            </div>\n" +
            "    \n" +
            "            \n" +
            "            <div class=\"col_2\">\n" +
            "             <br><a href=\"../GradingSystem.aspx\" id=\"Ag24\">Grading System</a>\n" +
            "             <br><a href=\"../Scholars.aspx\" id=\"As25\">Senior Scholars</a>         \n" +
            "             <br><a href=\"../Calendar.aspx\" id=\"Ax29\">Calenders &amp; Time Table</a>\n" +
            "            </div>\n" +
            "            <div class=\"col_3\">\n" +
            "                <p>Passion for Quality Education: Our Driving Force</p>\n" +
            "            </div>\n" +
            "          \n" +
            "        </div><!-- End 2 columns container -->\n" +
            "    \n" +
            "    </li><!-- End Programmes Item -->\n" +
            "    <li><a href=\"../International.aspx\" id=\"A6\" class=\"drop\">International</a><!-- Begin 5 columns Item -->\n" +
            "    \n" +
            "        <!-- End 5 columns container -->\n" +
            "    \n" +
            "    </li><!-- End 5 columns Item -->\n" +
            "         \n" +
            "    <li><a href=\"../Library.aspx\" id=\"A12\" class=\"drop\">Library</a><!-- Begin Library Item -->\n" +
            "    \n" +
            "        <div class=\"dropdown_5columns\"><!-- Begin Library columns container -->\n" +
            "    \n" +
            "            <div class=\"col_1\">\n" +
            "                <h2>Welcome to UNILUS Library</h2>\n" +
            "            </div>\n" +
            "    \n" +
            "            <div class=\"col_2\">\n" +
            "                <p>The University of Lusaka Library/Resource Centre was established to provide a focal point for the provision of information resources for academic and research for both the students and the members of staff</p>             \n" +
            "            </div>\n" +
            "            \n" +
            "            <div class=\"col_1\">\n" +
            "            <img src=\"../Images/lib0.jpg\" id=\"Img2\" width=\"100\" alt=\"UNILUS\">\n" +
            "            </div>\n" +
            "            \n" +
            "            <div class=\"col_3\">\n" +
            "            </div>\n" +
            "          \n" +
            "        </div><!-- End Library columns container -->\n" +
            "    \n" +
            "    </li><!-- End Library Item -->\n" +
            "    <li><a href=\"../Jobs.aspx\" id=\"Aj5\" class=\"drop\">Jobs</a><!-- Begin  Jobs Item -->\n" +
            "    \n" +
            "        <!-- End 5 columns container -->\n" +
            "    \n" +
            "    </li><!-- End Jobs  Item -->\n" +
            "   \n" +
            "    <li><a href=\"../#\" id=\"A10\" class=\"drop\">Contact Us</a>\n" +
            "    \n" +
            "\t\t<div class=\"dropdown_6columns\">\n" +
            "        \n" +
            "                <div class=\"col_1\">\n" +
            "               <table width=\"220px\" ;=\"\">                   \n" +
            "                    \n" +
            "                    <tbody><tr>\n" +
            "                        <td colspan=\"2\"><h2>Academic Office</h2></td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Email:</td>\n" +
            "                        <td><a href=\"mailto:academic@ictar.ac.zm\">academic@ictar.ac.zm</a></td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Phone:</td>\n" +
            "                        <td>+260 211 233407/258409</td>\n" +
            "                    </tr>\n" +
            "                </tbody></table>    \n" +
            "          </div>\n" +
            "\n" +
            "            <div class=\"col_2\">\n" +
            "                <table width=\"220px\" ;=\"\">\n" +
            "                    \n" +
            "                   <tbody><tr>\n" +
            "                        <td colspan=\"2\"><h2>The Deputy Vice Chancellor</h2></td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Name:</td>\n" +
            "                        <td>Prof. E. Kazonga</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Email:</td>\n" +
            "                        <td><a href=\"mailto:deputyvicechancellor@ictar.ac.zm\">deputyvicechancellor@ictar.ac.zm</a></td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Phone:</td>\n" +
            "                        <td>+260 211 233407/258409</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Fax:</td>\n" +
            "                        <td>+260 211 233409</td>\n" +
            "                    </tr>\n" +
            "                </tbody></table>                   \n" +
            "          </div>\n" +
            "        <div class=\"col_3\">\n" +
            "        <table width=\"220px\" ;=\"\">\n" +
            "                    <tbody><tr>\n" +
            "                        <td colspan=\"2\">\n" +
            "                          <h2>The Vice Chancellor</h2>                        \n" +
            "                     </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Name:</td>\n" +
            "                        <td>Prof. Pinalo Chifwanakeni</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Email:</td>\n" +
            "                        <td><a href=\"mailto:ictar@zamnet.zm\" title=\"email the CV\">ictar@zamnet.zm</a></td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td style=\"text-align: right\">Phone:</td>\n" +
            "                        <td>+260 211 233407/258409</td>\n" +
            "                    </tr>                   \n" +
            "                    \n" +
            "                </tbody></table>              \n" +
            "                                  \n" +
            "          </div>\n" +
            "\t\t</div>\n" +
            "        \n" +
            "\t</li>\n" +
            "    </ul>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<div class=\"main1\">\n" +
            " \n" +
            "    <table style=\"vertical-align: top\">\n" +
            "        <tbody><tr>\n" +
            "            <td class=\"stafftable1\">\n" +
            "                <div class=\"image\">\n" +
            "               <img src=\"../UserImages/ECA1713710.jpg\" alt=\"Student Image\" style=\"height: 140px; width: 120px\" id=\"Staff0\" runat=\"server\"> </div>\n" +
            "                 \n" +
            "                       <h3>Hi! SIBINDI</h3>\n" +
            "                    \n" +
            "                <div class=\"leftCol\">                    \n" +
            "                    <h1>Student Menu</h1>\n" +
            "                   \n" +
            "                    \n" +
            "                    <ul>\n" +
            "                        \n" +
            "                        <li><a href=\"/Students/ExamRegistration.aspx\" id=\"MainContent_Student0\">Exam Registration</a></li>                        \n" +
            "                       <li><a href=\"RegistrationForm.aspx\" id=\"MainContent_A19\">Registration form</a></li>\n" +
            "                        \n" +
            "                        \n" +
            "                        <li><a href=\"ViewCA.aspx\" id=\"MainContent_Student3\">View CA Results</a></li>\n" +
            "                        <li><a href=\"Statement.aspx\" id=\"MainContent_Student4\">Account Statement</a></li>\n" +
            "                        \n" +
            "                        <li><a href=\"UndergradTopicSubmission.aspx\" id=\"MainContent_A11\">Submit Research Topic</a></li>\n" +
            "                        <li><a href=\"Assignments.aspx\" id=\"MainContent_Student6\">Assignments</a></li>\n" +
            "                        <li><a href=\"SubmittedAssignments.aspx\" id=\"MainContent_A25\">My Submitted Assignments</a></li>\n" +
            "                        <li><a href=\"Materials.aspx\" id=\"MainContent_Student7\">Materials</a></li>\n" +
            "                        \n" +
            "                        <li><a href=\"MyAccount.aspx\" id=\"MainContent_A5\">My UNILUS details</a></li>                        \n" +
            "                        <li><a href=\"QuestionBank.aspx\" id=\"MainContent_Aq11\">Question Bank</a></li>\n" +
            "                        <li><a href=\"Forms.aspx\" id=\"MainContent_A10\">Correspondence Forms</a></li>\n" +
            "                        <li><a href=\"LecturerContact.aspx\" id=\"MainContent_A12\">Lecturers Contact</a></li>\n" +
            "                        <li><a href=\"UnderEvaluation.aspx\" id=\"MainContent_A17\">Lecturer Evaluation</a></li>  \n" +
            "                        <li><a href=\"DetailsConfirmation.aspx\" id=\"MainContent_A23\">Graduation Details Confirmation</a></li>                       \n" +
            "                    </ul>\n" +
            "                    \n" +
            "                 </div>\n" +
            "            </td>\n" +
            "            <td style=\"vertical-align: top\" class=\"style2\">\n" +
            "                <div class=\"undergradMain\">\n" +
            "                    \n" +
            "    <h1>Lecturer conracts</h1>\n" +
            "<p>\n" +
            "    </p><table style=\"width: 100%\">\n" +
            "<tbody><tr>\n" +
            "<td><b>Course</b></td>\n" +
            "<td><b>Name</b></td>\n" +
            "<td><b>Cell</b></td>\n" +
            "<td><b>Email</b></td>\n" +
            "</tr>\n" +
            "<tr class=\"grdEvenRow\">\n" +
            "<td style=\"width: 8%\">ECF110</td>\n" +
            "<td style=\"width: 8%\">Mr. Teddy  Funyina </td>\n" +
            "<td style=\"width: 8%\">0978266991</td>\n" +
            "<td style=\"width: 8%\">funyinatk@unilus.ac.zm</td>\n" +
            "</tr>\n" +
            "<tr class=\"grdOddRow\">\n" +
            "<td style=\"width: 8%\">HRM100</td>\n" +
            "<td style=\"width: 8%\">Mr. JEFFREY MULEYA</td>\n" +
            "<td style=\"width: 8%\">977807792</td>\n" +
            "<td style=\"width: 8%\">muleyajeffrey@gmail.com</td>\n" +
            "</tr>\n" +
            "<tr class=\"grdEvenRow\">\n" +
            "<td style=\"width: 8%\">AFIN102</td>\n" +
            "<td style=\"width: 8%\">Mr. Jackson  Sishumba</td>\n" +
            "<td style=\"width: 8%\">0967829219</td>\n" +
            "<td style=\"width: 8%\">sishumbacj@yahoo.com</td>\n" +
            "</tr>\n" +
            "<tr class=\"grdOddRow\">\n" +
            "<td style=\"width: 8%\">ECF110</td>\n" +
            "<td style=\"width: 8%\">Mr. Ibrahim Kamara</td>\n" +
            "<td style=\"width: 8%\">0977607694</td>\n" +
            "<td style=\"width: 8%\">ibrahimkamara930@gmail.com</td>\n" +
            "</tr>\n" +
            "</tbody></table>\n" +
            "\n" +
            "<p></p>\n" +
            "\n" +
            "                </div>\n" +
            "           </td>\n" +
            "        </tr>\n" +
            "    </tbody></table>\n" +
            "\n" +
            "\n" +
            "       </div>\n" +
            "         <div class=\"clear\"></div>\n" +
            "        <div class=\"footer\">\n" +
            "    <div class=\"footer1\">\n" +
            "    </div>\n" +
            "        University of Lusaka, Plot No 37413, Off Alick Nkata Road, Mass Media.<br>P.O. Box 36711, Lusaka-Zambia.<br>\n" +
            "        Phone: +260 211 258505 / +260 211 258409<br>\n" +
            "        Fax: +260 211 233409<br>\n" +
            "        <br>General queries: +260 976 075 850 / +260 953 688 533 / +260 961 917 862\n" +
            "        <br>Undergraduate queries: +260 976 200 094\n" +
            "        <br>Postgraduate queries: +260 977 377 430\n" +
            "        <br>IT queries: +260 950 100 152 or +260 964 777 661<br>\n" +
            "        <a href=\"https://www.facebook.com/unilusofficial/\" target=\"_blank\"><div class=\"facebook\"></div></a>\n" +
            "    </div>\n" +
            "  <div class=\"footer1\">\n" +
            "    </div>\n" +
            "  </form>\n" +
            " \n" +
            "\n" +
            "</body></html>";

}
