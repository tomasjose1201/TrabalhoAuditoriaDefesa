<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .modal-header, h4, .close {
                background-color: #5cb85c;
                color:white !important;
                text-align: center;
                font-size: 30px;
            }
            .modal-footer {
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>Simulador de SQL Injection (Defesa)</h2>
            <!-- Trigger the modal with a button -->
            <button type="button" class="btn btn-default btn-lg" id="myBtn">Login</button>

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header" style="padding:35px 50px;">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
                        </div>
                        <div class="modal-body" style="padding:40px 50px;">
                            <form action="LoginController" method="POST">
                                <div class="form-group">
                                    <label for="usrname"><span class="glyphicon glyphicon-user"></span> Email</label>
                                    <input type="email" class="form-control" name="email" placeholder="Digite seu email" required>
                                </div>
                                <div class="form-group">
                                    <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Senha</label>
                                    <input type="password" class="form-control" name="senha" placeholder="Digite sua senha" required>
                                </div>
                                <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div> 
        </div>

        <script>
            $(document).ready(function () {
                $("#myBtn").click(function () {
                    $("#myModal").modal();
                });
            });
        </script>

    </body>
</html>