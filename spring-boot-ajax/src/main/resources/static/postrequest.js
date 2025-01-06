$(document).ready(
    function() {
        // FORMU GONDERMEK - SUBMIT
        $("#bookForm").submit(function(event) {
            event.preventDefault(); //kullanılarak formun tarayıcı üzerinden varsayılan gönderimi engellendi. Bu sayede form verileri manuel işlenebilir hale getirildi.
            //Form gönderimi sırasında tarayıcının sayfayı yeniden yüklemesini engeller.
            //Linklere tıklanıldığında varsayılan yönlendirmeyi durdurabilir.
            //Tarayıcıda otomatik yenileme veya yönlendirme gibi davranışlar kontrol altına alınır.

            ajaxPost();
        });

        function ajaxPost() {
            // FORM VERISINI HAZIRLAMA
            var formData = {
                bookId : $("#bookId").val(),
                bookName : $("#bookName").val(),
                author : $("#author").val()
            }

            // POST ISLEMI KARSILAMA
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "saveBook",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status == "success") {
                        $("#postResultDiv").html(
                                "" + result.data.bookName
                                        + "Gönderme işlemi başarılı(POST)! <br>"
                                        + "---> Hayırlı Olsun hacım !!" + "</p>");
                    } else {
                        $("#postResultDiv").html("<strong>Hata oldu!</strong>");
                    }
                    console.log(result);
                },
                error : function(e) {
                    alert("Hata!")
                    console.log("Hata: ", e);
                }
            });
        }
    }
)