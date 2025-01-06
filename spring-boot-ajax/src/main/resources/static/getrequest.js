GET: $(document).ready(
    function() {

        // GET ISTEGI
        $("#getALlBooks").click(function(event) {
            event.preventDefault();
            ajaxGet();
        });

        // GET ISTEGI KARSILAMA
        function ajaxGet() {
            $.ajax({
                type : "GET",
                url : "getBooks",
                success : function(result) {
                    if (result.status == "success") {
                        $('#getResultDiv ul').empty();
                        var custList = "";
                        $.each(result.data,
                                function(i, book) {
                                    var user = "Kitap Adı  "
                                            + book.bookName
                                            + ", Yazar  = " + book.author
                                            + "<br>";
                                    $('#getResultDiv .list-group').append(
                                            user)
                                });
                        console.log("Basarili: ", result);
                    } else {
                        $("#getResultDiv").html("<strong>Hata oldu!</strong>");
                        console.log("Hata: ", result);
                    }
                },
                error : function(e) {
                    $("#getResultDiv").html("<strong>Hata oldu!</strong>");
                    console.log("HATA!: ", e);
                }
            });
        }
    }
)