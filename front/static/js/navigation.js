import { getSelected, createPecSelect, createProtocoleSelect } from './scripts.js';

var ctnmUrl = "http://localhost:8080/api/consultation/ctnm";
var tnmUrl = "http://localhost:8080/api/consultation/tnm";
var pecUrl = "http://localhost:8080/api/consultation/pec";
var traitementUrl = "http://localhost:8080/api/treatment/chimio";

document.addEventListener("DOMContentLoaded", function () {

    var profil_button = document.getElementById("profil-continue");
    var habitude_button = document.getElementById("habitude-continue");    
    var antcd_button = document.getElementById("atcd-continue");
    var clinique_button = document.getElementById("clinique-continue");
    var examen_button = document.getElementById("examen-continue");
    var imag_button = document.getElementById("imagerie-continue");
    var diag_button = document.getElementById("diag-continue");
    var ctnm_button = document.getElementById("ctnm-continue");
    var anatomo_button = document.getElementById("anato-continue");
    var bilan_ex = document.getElementById("bilan-ext-continue");
    var bilan_pre = document.getElementById("bilan-pre-continue");
    var traitement_button = document.getElementById("traitement-continue");

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

    if (imag_button) {
        imag_button.addEventListener('click', navigate_imagerie)
    }

    if (diag_button) {
        diag_button.addEventListener('click', navigate_diag)
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

    if (bilan_pre) {
        bilan_pre.addEventListener('click', navigate_bilan_pre);
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

    function navigate_imagerie() {
        document.getElementById("diag-h").click();
    }

    function navigate_diag() {
        document.getElementById("ctnm-h").click();
    }

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
            console.log(body["ctnm"]);
            display.removeAttribute("hidden");
            span.innerHTML = body["ctnm"];
        }).catch(error => {
            console.log(error)
        })

        document.getElementById("antomo-h").click();

    }
    function navigate_anatomo() {

        localStorage.clear();
        localStorage.setItem("hist", document.getElementById("hist").value);
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
            "irmrachid": irmrachid,
            "hist": `${localStorage.getItem("hist")}`
        }
        console.log(data)

        fetch(tnmUrl, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data), 
        }).then(res => res.json())
        .then(body => {
            console.log(body['tnm']);
            console.log(body['stade']);
            document.getElementById("tnm-display").removeAttribute("hidden");
            document.getElementById("tnm-span").innerHTML += body['tnm'];
            document.getElementById("stade-display").removeAttribute("hidden");
            document.getElementById("stade-span").innerHTML += body['stade'];
        }).catch(error => {
            console.log(error);
        })

        document.getElementById("bilan-pre-h").click();
    
    }

    function navigate_bilan_pre() {

        var histo = document.getElementById("hist").value;
        var paco2 = document.getElementById("paco2").value;
        if (paco2 == 0) {
            paco2 = "N/A"
        } else if (paco2 > 45) {
            paco2 = "SUP45"
        } else {
            paco2 = "INF45"
        }
        var ps = document.getElementById("ps").value;
        if (ps == "") {
            ps = "N/A"
        }
        var vems = document.getElementById("vems").value;
        if (vems == 0) {
            vems = "N/A"
        } else if (vems <= 1) {
            vems = "INF1"
        } else {
            vems = "SUP1"
        }
        
        let data = {
            "histo": `${histo}`,
            "paco2":  `${paco2}`,
            "vems": `${vems}`,
            "ps": `${ps}`
        }
        fetch(pecUrl, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        }).then(res => res.json())
        .then(body => {
            
             // create the label
            var label = document.createElement("label")
            label.innerHTML = "Prise en charge:&nbsp;&nbsp"
            document.getElementById("traitement").appendChild(label)

            // dynamically create a select and input tag holding the pec values
            var select = createPecSelect(body)
            var breakLine = document.createElement("br")

            document.getElementById("traitement").appendChild(select)
            
            // break line
            document.getElementById("traitement").appendChild(breakLine)

            var button = document.createElement("input")
            button.classList.add("btn")
            button.type = "submit"
            button.value = "Continuer"
            button.id = "traitement-continue"
            button.addEventListener('click', navigate_traitement)
            document.getElementById("traitement").appendChild(button)

            document.getElementById("traitement-h").click()
        }).catch(error => {
            var label = document.createElement("label")
            label.innerHTML = error.message
        })
    }

    function navigate_traitement() {
        
        var pec = document.getElementById("pec-select")
        var traitement = pec.options[pec.selectedIndex].innerHTML
        console.log(traitement)
        if(traitement == "Chimiothérapie") {
            var histo = document.getElementById("hist").value
            var stade = document.getElementById("stade-span").innerHTML
            var vems = document.getElementById("vems").value
            if (vems == 0) {
                vems = "N/A"
            } else if (vems <= 1) {
                vems = "INF1"
            } else {
                vems = "SUP1"
            }
            var paco2 = document.getElementById("paco2").value
            if (paco2 == 0) {
                paco2 = "N/A"
            } else if (paco2 > 45) {
                paco2 = "SUP45"
            } else {
                paco2 = "INF45"
            }
            var type_histo = document.getElementById("hist-type").value
            var clairance = document.getElementById("clairance").value
            if (clairance >= 60) {
                clairance = "SUP60"
            } else if (clairance < 60 && clairance >= 30) {
                clairance = "INF60"
            } else if (clairance < 30 && clairance > 0){
                clairance = "INF30"
            } else {
                clairance = "N/A"
            }
            
            var audiometrie = document.getElementById("audiometrie").value
            var egfr = document.getElementById("egfr").value
            var alk = document.getElementById("alk").value
            var braf = document.getElementById("braf").value
            var ros1 = document.getElementById("ros1").value
            var pdl1 = document.getElementById("pdl1").value
            var ps = document.getElementById("ps").value
            if (ps == "") {
                ps = "N/A"
            }

            let data = {
                "histo": `${histo}`,
                "stade": `${stade}`,
                "vems": `${vems}`,
                "paco2": `${paco2}`,
                "type_histo": `${type_histo}`,
                "clairance": `${clairance}`,
                "audiometrie": `${audiometrie}`,
                "egfr": `${egfr}`,
                "alk": `${alk}`,
                "braf": `${braf}`,
                "ros1": `${ros1}`,
                "pdl1": `${pdl1}`,
                "ps": `${ps}`,
                "tabac": "N/A"
            }
            console.log(data)

            fetch(traitementUrl, {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify(data)
            }).then(res => res.json())
            .then(body => {
                console.log(body['protocole'])
                createProtocoleSelect(body['protocole'])

            }).catch(error => {
                console.log(error)
            })

        } else {
            var container = document.getElementById("protocole")
            var htmlBlock = document.createElement("div")
            htmlBlock.innerHTML = "<p>Could not generate Protocole suggetion</p>"
            container.appendChild(htmlBlock)
        }
        document.getElementById("protocole-h").click()
    }

});


