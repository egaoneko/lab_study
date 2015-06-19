<#--
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/13/15
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 -->

<@layout.extends name="layouts/default.ftl">
    <@layout.put block="head" type="replace">
        <title>${myApp.name}</title>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Index Page">
        <meta name="author" content="Donghyun Seo">

        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.4/css/bootstrap.min.css" />

        <!-- Custom Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="/startbootstrap-creative/font-awesome/css/font-awesome.min.css" type="text/css">

        <!-- Plugin CSS -->
        <link rel="stylesheet" href="/startbootstrap-creative/css/animate.min.css" type="text/css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="/startbootstrap-creative/css/creative.css" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </@layout.put>

    <@layout.put block="header" type="prepend">
        <@layout.extends name="layouts/index/header.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="contents">
        <section class="bg-primary" id="about">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 text-center">
                        <h2 class="section-heading">study together!</h2>
                        <hr class="light">
                        <p class="text-faded">Success doesn't come to anyone, but it comes to the self-controlled and the hard-working.The payoff of efforts never disappear without redemption!</p>
                        <a href="#" class="btn btn-default btn-xl">Get Started!</a>
                    </div>
                </div>
            </div>
        </section>

        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">At Your Service</h2>
                        <hr class="primary">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-diamond wow bounceIn text-primary"></i>
                            <h3>Sturdy Templates</h3>
                            <p class="text-muted">Our website provides the space for creating the study group.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-paper-plane wow bounceIn text-primary" data-wow-delay=".1s"></i>
                            <h3>Ready to learn</h3>
                            <p class="text-muted">You can study with various people!</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-newspaper-o wow bounceIn text-primary" data-wow-delay=".2s"></i>
                            <h3>Up to Date</h3>
                            <p class="text-muted">Everyday new study group will be opened.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="service-box">
                            <i class="fa fa-4x fa-heart wow bounceIn text-primary" data-wow-delay=".3s"></i>
                            <h3>Skills Development</h3>
                            <p class="text-muted">Create a portfolio of your own!</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <aside class="bg-dark" id="portfolio">
            <div class="container text-center">
                <div class="call-to-action">
                    <h2> Let's go GitHub! </h2>
                    <a href="https://github.com/egaoneko/lab_study" class="btn btn-default btn-xl wow tada">GitHub</a>
                </div>
            </div>
        </aside>

        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 text-center">
                        <h2 class="section-heading">Let's Get In Touch!</h2>
                        <hr class="primary">
                        <p>Do you have any questions for us?? Give us a call or send us an email and we will get back to you as soon as possible!</p>
                    </div>
                    <div class="col-lg-4 col-lg-offset-2 text-center">
                        <i class="fa fa-phone fa-3x wow bounceIn"></i>
                        <p>010-0000-0000</p>
                    </div>
                    <div class="col-lg-4 text-center">
                        <i class="fa fa-envelope-o fa-3x wow bounceIn" data-wow-delay=".1s"></i>
                        <p><a href="mailto:your-email@your-domain.com">cbocho90@naver.com</a></p>
                    </div>
                </div>
            </div>
        </section>
    </@layout.put>

    <@layout.put block="footer" type="replace">
        <@layout.extends name="layouts/footer.ftl">
        </@layout.extends>
    </@layout.put>

    <@layout.put block="script" type="replace">
        <!-- jQuery -->
        <script src="/webjars/jquery/2.1.4/jquery.min.js"></script>

        <!-- Bootstrap Core JS -->
        <script src="/webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>

        <!-- Plugin JavaScript -->
        <script src="/startbootstrap-creative/js/jquery.easing.min.js"></script>
        <script src="/startbootstrap-creative/js/jquery.fittext.js"></script>
        <script src="/startbootstrap-creative/js/wow.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="/startbootstrap-creative/js/creative.js"></script>
    </@layout.put>
</@layout.extends>