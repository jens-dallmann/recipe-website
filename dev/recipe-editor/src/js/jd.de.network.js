var getToMain = function(recipeId) {
    $.ajax({
          type: "GET",
          url: "/recipe-editor/recipe/"+recipeId,
          beforeSend: function(xhr) {
                              xhr.setRequestHeader("Content-Type", "application/json");
                          },
          success: function(data) {
            $("#main").html('');
            $("#main").append(data);
          }
        });
}