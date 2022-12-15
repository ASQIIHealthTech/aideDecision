import { disbaleEnableInput } from './scripts.js';

document.addEventListener("DOMContentLoaded", function() {
    var tabac = document.getElementById("tabac-actif");
    var sevrage = document.getElementById("servage");
    var symp_resp = document.getElementById("symptomes-respiratoires");
    var symp_env = document.getElementById("envahissement-local");
    var symp_env_dist = document.getElementById("envahissement-distance");
    var symp_parane = document.getElementById("syndromes-paraneoplasiques");
    var check_radio_thorax = document.getElementById("radiothorax_normal");
    var fibroscopie = document.getElementById("fibroscopie");
    var mediascopie = document.getElementById("mediastinoscopie");
    var thoracotomie = document.getElementById("thoracotomie");
    var thoracoscopie = document.getElementById("thoracoscopie");
    var echoendoscopie = document.getElementById("echoendoscopie");

    if (tabac) {
        tabac.addEventListener('change', tabac_condition);
    }

    if (sevrage) {
        sevrage.addEventListener('change', sevrage_condition);
    }

    if (symp_resp && symp_env && symp_env_dist) {
        symp_resp.addEventListener('change', clinique_condition);
        symp_env.addEventListener('change', clinique_condition);
        symp_env_dist.addEventListener('change', clinique_condition);
        symp_parane.addEventListener('change', clinique_condition);
    }

    if (fibroscopie) {
        fibroscopie.addEventListener('change', diag_condition);
        mediascopie.addEventListener('change', diag_condition);
        thoracotomie.addEventListener('change', diag_condition);
        thoracoscopie.addEventListener('change', diag_condition);
        echoendoscopie.addEventListener('change', diag_condition);

    }

    if (check_radio_thorax) {
        check_radio_thorax.addEventListener('change', function() {
            if (this.checked) {
                document.getElementById("opacite").disabled = true;
                document.getElementById("Adenopathies").disabled = true;
                document.getElementById("epanchement-pleural").disabled = true;
                document.getElementById("anomalie-osseuse").disabled = true;                
            } else {
                document.getElementById("opacite").disabled = false;
                document.getElementById("Adenopathies").disabled = false;
                document.getElementById("epanchement-pleural").disabled = false;
                document.getElementById("anomalie-osseuse").disabled = false;    
            }   
        });
    }

    function tabac_condition() {
        var tabac_actif = document.getElementById("tabac-actif").value;

        if (tabac_actif == "Non") {
            document.getElementById("tabac-passif-label").removeAttribute("hidden");
            document.getElementById("tabac-passif").removeAttribute("hidden");

            document.getElementById("pa-label").setAttribute("hidden", "");
            document.getElementById("pa").setAttribute("hidden", "");

            document.getElementById("servage-label").setAttribute("hidden", "");
            document.getElementById("servage").setAttribute("hidden", "");

            document.getElementById("temps-label").setAttribute("hidden", "");
            document.getElementById("temps-id").setAttribute("hidden", "");
            
        } else {
            document.getElementById("pa-label").removeAttribute("hidden");
            document.getElementById("pa").removeAttribute("hidden");

            document.getElementById("tabac-passif-label").setAttribute("hidden", "");
            document.getElementById("tabac-passif").setAttribute("hidden", "");

            document.getElementById("servage-label").removeAttribute("hidden");
            document.getElementById("servage").removeAttribute("hidden");
        }
    };

    function sevrage_condition() {
        var servage_value = document.getElementById("servage").value;

        if(servage_value == "Oui") {
            document.getElementById("temps-label").removeAttribute("hidden");
            document.getElementById("temps-id").removeAttribute("hidden");
        } else {
            document.getElementById("temps-label").setAttribute("hidden", "");
            document.getElementById("temps-id").setAttribute("hidden", "");
        }
    };

    function clinique_condition () {
       var input_symp_resp = document.getElementById("temps-signe-associe");
       var input_sym_env = document.getElementById("temps-envahissement-local");
       var input_sym_env_dist = document.getElementById("temps-envahissement-distance");
       var input_sym_parane = document.getElementById("temps-syndromes-paraneoplasiques");

       disbaleEnableInput(symp_resp, input_symp_resp, '');
       disbaleEnableInput(symp_env, input_sym_env, '');
       disbaleEnableInput(symp_env_dist, input_sym_env_dist, '');
       disbaleEnableInput(symp_parane, input_sym_parane, '');

    }

    function diag_condition() {
        var input_fibroscopie = document.getElementById("Biopsie_a");
        var input_mediascopie = document.getElementById("Biopsie_b");
        var input_thoracotomie = document.getElementById("Biopsie_c");
        var input_thoracoscopie = document.getElementById("Biopsie_d");
        var input_echoendoscopie = document.getElementById("Biopsie_e");

        disbaleEnableInput(fibroscopie, input_fibroscopie, "Non");
        disbaleEnableInput(mediascopie, input_mediascopie, "Non");
        disbaleEnableInput(thoracotomie, input_thoracotomie, "Non");
        disbaleEnableInput(thoracoscopie, input_thoracoscopie, "Non");
        disbaleEnableInput(echoendoscopie, input_echoendoscopie, "Non");

    }
});