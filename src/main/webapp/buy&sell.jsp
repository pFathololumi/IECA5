<!DOCTYPE html>
<html>
<head>
    
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>خرید و فروش سهام</title>

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
                                        <h3>بررسی و انجام درخواست خرید و فروش سهام</h3>
                                    </div>
                                    <div class="form-top-right">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </div>
                                </div>
                                <div class="form-bottom">
                                    <form role="form" class="registration-form" action="http://localhost:9091/order/sell" method="get">
                                        <div class="form-group">
                                            <label class="sr-only">نام کاربری</label>
                                            <input type="text" name="id" placeholder="...نام کاربری" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only">شناسه سهام</label>
                                            <input type="text" name="instrument" placeholder="...شناسه سهام" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only">قیمت پیشنهادی</label>
                                            <input type="text" name="price" placeholder="...قیمت پیشنهادی" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only">مقدار سهام</label>
                                            <input type="text" name="quantity" placeholder="...مقدار سهام" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only">نوع درخواست</label>
                                            <input type="text" name="type" placeholder="...نوع درخواست" class="form-control">
                                        </div>
										<div class="form-row">
										
										<div class="form-radio-buttons">

										 <div>
											<label>
												<input type="radio" name="buy">
												<span>خرید</span>
											</label>
										</div>

										<div>
											<label>
												<input type="radio" name="sell">
												<span>فروش</span>
											</label>
										</div>

										</div>

										
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
