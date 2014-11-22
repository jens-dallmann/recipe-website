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

    var postRecipe = function() {
        var recipe = {
            "title":$('#recipeTitle').val(),
            "id":$('#recipeId').val(),
            "images": new Array()
        }
        var imageArray = $('#imagegallery > li > img')
        imageArray.each(function(img) {
            recipe.images.push(this.src)
        })
        $.ajax({
          type: "POST",
          url: "/recipe-editor/recipe/add",
          data: JSON.stringify(recipe),
          beforeSend: function(xhr) {
                              xhr.setRequestHeader("Content-Type", "application/json");
                          },
          success: function(data) {
            document.write(data);
          }
        });
        console.log(recipe);
    }
    $('#gallery-upload').on('change', imageUpload);
    $('#submit').on('click', postRecipe)


});

  //previewFile();  //calls the function named previewFile()