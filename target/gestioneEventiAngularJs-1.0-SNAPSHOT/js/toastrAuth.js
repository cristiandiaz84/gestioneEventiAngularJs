var notification = (function () {

    var isNotEmpty = function () {

        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        
        if ($('#user').val().length === 0) {
            toastr.error('"Username" must not be empty!');
            return false;
        }

        if ($('#psw').val().length === 0) {
            toastr.error('"Password" must not be empty!');
            return false;
        }

        return true;

    };

    return {
        isEmpty: isNotEmpty
    };

}());