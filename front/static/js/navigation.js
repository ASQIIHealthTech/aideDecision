import { getSelected } from './scripts.js';

var ctnmUrl = "http://localhost:8080/api/consultation/ctnm";
var tnmUrl = "http://localhost:8080/api/consultation/tnm";


document.addEventListener("DOMContentLoaded", function () {

    var profil_button = document.getElementById("profil-continue");
    var habitude_button = document.getElementById("habitude-continue");    
    var antcd_button = document.getElementById("atcd-continue");
    var clinique_button = document.getElementById("clinique-continue");
    var examen_button = document.getElementById("examen-continue");
    var ctnm_button = document.getElementById("ctnm-continue");
    var anatomo_button = document.getElementById("anato-continue");
    var bilan_ex = document.getElementById("bilan-ext-continue");


    if (profil_button) {
        profil_button.addEventListener('click', navigation_profil);
    }

    if (habitude_button) {
        habitude_button.addEventListener('click', navigation_habitude);
    }

    if (antcd_button) {
        antcd_button.addEventListener('click', navigation_antcd);
    }

    if (clinique_button) {
        clinique_button.addEventListener('click', navigation_clinique);
    }

    if (examen_button) {
        examen_button.addEventListener('click', navigation_examen);
    }

    if (ctnm_button) {
        ctnm_button.addEventListener('click', navigate_ctnm);
    }

    if(anatomo_button) {
        anatomo_button.addEventListener('click', navigate_anatomo);
    }

    if(bilan_ex) {
        bilan_ex.addEventListener('click', navigate_bilan);
    }

    function navigation_profil() {
         // get the data
        var sexe = document.getElementById("sexe-select").value;
        var age = document.getElementById("age").value;

        // local storage
        localStorage.setItem("sexe", sexe);
        localStorage.setItem("age", age);

        // set the icon to unhidden
        document.getElementById("sexe-display").removeAttribute("hidden");
        document.getElementById("age-display").removeAttribute("hidden");

        // navigate to the next onglet
        document.getElementById("habitude-h").click();

        // display data
        document.getElementById("sexe-span").innerHTML =  sexe + "&nbsp &nbsp &nbsp &nbsp";
        document.getElementById("age-span").innerHTML = age + "&nbsp &nbsp &nbsp &nbsp";
    };

    function navigation_habitude() {
        // get the data
        var tabac_actif = document.getElementById("tabac-actif").value;
        var paquet = document.getElementById("pa").value;

        // local storage
        localStorage.setItem("tabac-actif", tabac_actif);
        localStorage.setItem("paquet-annee", paquet);

        // set the icon to unhidden
        document.getElementById("tabagisme-display").removeAttribute("hidden");
        document.getElementById("pa-display").removeAttribute("hidden");

        // navigate to the next onglet
        document.getElementById("antcd-h").click();

        //display the data
        if (tabac_actif == 'Oui') {
            document.getElementById("tabagisme-span").innerHTML = "Actif &nbsp &nbsp &nbsp &nbsp";
            document.getElementById("pa-span").innerHTML = paquet + "&nbsp &nbsp &nbsp &nbsp"; 
        } else {
            document.getElementById("tabagisme-span").innerHTML = "Passif &nbsp &nbsp &nbsp &nbsp";
            document.getElementById("pa-display").setAttribute("hidden", "");
        } 
    };

    
    function navigation_antcd() {

        // get the data
        var atcd_med = getSelected(document.getElementById("atcd-medicaux"));
        var atcd_chirugie = document.getElementById("atcd-chirurgicaux").value;
        var atcd_thorique = document.getElementById("radio_thoracique").value;
        var atcd_fam = document.getElementById("atcd-familiaux").value;

        // local storage
        localStorage.setItem("atcd_chirurgie", atcd_chirugie);
        localStorage.setItem("atcd_thorique", atcd_thorique);
        localStorage.setItem("atcd_fam", atcd_fam);

        // set icon to unhidden
        document.getElementById("atcd-med-display").removeAttribute("hidden");
        document.getElementById("atcd-chir-display").removeAttribute("hidden");
        document.getElementById("atcd-thor-display").removeAttribute("hidden");
        document.getElementById("atcd-fam-display").removeAttribute("hidden");

        // navigate to the next onglet
        document.getElementById("clinique-h").click();

        // display data
        document.getElementById("atcd-med-span").innerHTML = atcd_med;
        document.getElementById("atcd-chir-span").innerHTML = atcd_chirugie;
        document.getElementById("atcd-thor-span").innerHTML = atcd_thorique;
        document.getElementById("atcd-fam-span").innerHTML = atcd_fam;
        
    };

    function navigation_clinique() {
        //get the data

        //local storage

        //navigate to the next onglet
        document.getElementById("examen-h").click();
        
    };

    function navigation_examen() {
        //get the data
        var poid = document.getElementById("poid").value;
        var taille = document.getElementById("taille").value;
        var oms = document.getElementById("oms").value;
        var spo2 = document.getElementById("spo2").value;

        // local storage
        localStorage.setItem("poids", poid);
        localStorage.setItem("taille", taille);
        localStorage.setItem("oms", oms);
        localStorage.setItem("spo2", spo2);

        // set icon to unhidden 
        document.getElementById("poid-display").removeAttribute("hidden");
        document.getElementById("taille-display").removeAttribute("hidden");
        document.getElementById("oms-display").removeAttribute("hidden");
        document.getElementById("spo2-display").removeAttribute("hidden");


        // navigate to the next onglet 
        document.getElementById("imagerie-h").click();

        // display data
        document.getElementById("poid-span").innerHTML = poid + "Kg" + "&nbsp &nbsp &nbsp &nbsp";
        document.getElementById("taille-span").innerHTML = taille + "cm" + "&nbsp &nbsp &nbsp &nbsp";
        document.getElementById("oms-span").innerHTML = oms + "&nbsp &nbsp &nbsp &nbsp";
        document.getElementById("spo2-span").innerHTML = spo2 + "&nbsp &nbsp &nbsp &nbsp";
        
    };

    function navigate_ctnm() {
        var t = document.getElementById("t").value;
        var n = document.getElementById("n").value;
        var taille = document.getElementById("taille-tumeur").value;
        var m = document.getElementById("m").value;

        var data = {
            "t": t,
            "n": n,
            "taille": taille,
            "m": m
        }

        fetch(ctnmUrl, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data),
        }).then(res => res.json())
        .then((body) => {
            let display = document.getElementById("ctnm-display");
            let span = document.getElementById("ctnm-span");
            console.log(display);
            console.log(body["ctnm"]);
            display.removeAttribute("hidden");
            span.innerHTML = body["ctnm"];
            
        }
        )
         

        document.getElementById("antomo-h").click();

    }
    function navigate_anatomo() {

        document.getElementById("bilan-h").click();
    }

    function navigate_bilan() {
        var petscan = document.getElementById("pet-scan").value; 
        var tdmabdo = document.getElementById("tdm-abdo").value; 
        var tdmcere = document.getElementById("tdm-cere").value; 
        var scin = document.getElementById("scin").value; 
        var irmcere = document.getElementById("irm-cere").value; 
        var irmrachid = document.getElementById("irm-rachid").value; 

        let data = {
            "petscan": petscan,
            "tdmabdo": tdmabdo,
            "tdmcere": tdmcere,
            "scin": scin,
            "irmcere": irmcere,
            "irmrachid": irmrachid
        }

        fetch(tnmUrl, {
            method: "post",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data), 
        }).then(res => res.json())
        .then(body => {
            console.log(body['tnm']);
            document.getElementById("tnm-display").removeAttribute("hidden");
            document.getElementById("tnm-span").innerHTML = body['tnm'];
        })

        document.getElementById("bilan-pre-h").click();
    
    }

});


