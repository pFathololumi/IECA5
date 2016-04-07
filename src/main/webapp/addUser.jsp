<!DOCTYPE html>
<html>
<head>
    
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>اضافه کردن کاربر</title>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="style/myStyle.css" />
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="form/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="form/form-elements.css">
    <link rel="stylesheet" href="form/style.css">
</head>
<body>

    <jsp:include page="/menu.jsp" />
	<jsp:include page="/header.jsp" />


        <div class="passage page">
            <!-- Top content -->
            <div class="top-content">

                <div class="inner-bg">
                    <div class="container">

                        <div class="row">

                            <div class="col-sm-5 form-box">
                                <div class="form-top">
                                    <div class="form-top-left">
                                        <h3>افزودن کاربر جدید</h3>
                                    </div>
                                    <div class="form-top-right">
                                        <i class="fa fa-plus"></i>
                                    </div>
                                </div>
                                <div class="form-bottom">
                                    <form role="form" class="registration-form" action="selectBuyOrSell.jsp" method="post">
                                        <div class="form-group">
                                            <label class="sr-only">شناسه کاربری</label>
                                            <input type="text" name="id" placeholder="...شناسه کاربری" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only">نام</label>
                                            <input type="text" name="instrument" placeholder="...نام" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only">نام خانوادگی</label>
                                            <input type="text" name="price" placeholder="...نام خانوادگی" class="form-control">
                                        </div>
                                        
                                        <button type="submit" class="btn">ارسال درخواست</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

         <jsp:include page="/footer.jsp" /> 
</body>
</html>
