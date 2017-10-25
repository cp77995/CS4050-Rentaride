<?php

echo '
    <script src="carousel.js"></script>
    <link href="carousel.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Sansita" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    </head>
<body>
    <section class="slide-wrapper">
        <div class="container">
            <div id="myCarousel" class="carousel slide">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                 </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item item1 active">
                        <div class="fill" style=" background-color:#48c3af;">
                            <div class="inner-content">
                                <div class="carousel-img">
                                    <img src="hatchback.png" alt="hatchback" class="img img-responsive" />
                                </div>
                                <div class="carousel-desc">

                                    <h2>Picking up groceries?</h2>
                                    <p>Rates starting from $10/hour!</p>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="item item2">
                        <div class="fill" style="background-color:#b33f4a;">
                            <div class="inner-content">
                                <div class="carousel-img">
                                    <img src="truck.png" alt="truck" class="img img-responsive" />
                                </div>
                                <div class="carousel-desc">

                                    <h2>Bought a new flatscreen?</h2>
                                    <p>Rates starting from $15/hour!</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="item item3">
                        <div class="fill" style="background-color:#7fc2f4;">
                            <div class="inner-content">
                                <div class="carousel-img">
                                    <img src="moving truck.png" alt="truck" class="img img-responsive" />
                                </div>
                                <div class="carousel-desc">

                                    <h2>Moving?</h2>
                                    <p>Rates starting from $20/hour!</p>

                                </div>
                            </div>
                        </div>
                    </div>
                    

                </div>
            </div>
        </div>
    </section>
</body>
</html>
';
                    
?>