$("document").ready(function(){
        var imageUpload = function() {
        //file selected from the user
       var file    = document.querySelector('input[type=file]').files[0]; //sames as here

       //select image tag.
       var reader  = new FileReader();

       //register listener on file reader.
       reader.onloadend = function (e) {
           var image = {
               alt:'Bratkartoffeln',
               id: 'imagegallery-image'
           }
           image.src =  e.target.result;

           var img = $('<img />', image);
           var imageGalleryItem = {
            id: '#imagegallery-item'
           }
           var liElement = $('<li >', imageGalleryItem);

           $('#imagegallery').append(liElement);

           liElement.appendTo('#imagegallery');
           liElement.append(img);
       }

       if (file) {
           //start reading the file.
           reader.readAsDataURL(file);
       }
    }
    $('#gallery-upload').on('change', imageUpload);
});

  //previewFile();  //calls the function named previewFile()