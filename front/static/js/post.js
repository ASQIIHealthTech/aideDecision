var save_consultation_url = "http://localhost:8080/api/consultation/save";
var save_patient_url = "http://localhost:8080/api/patients/new";

document.addEventListener("DOMContentLoaded", function () {
  var save_button = document.getElementById("save-btn");
  var save_patient_button = document.getElementById("save-btn-pat");

  if (save_button) {
    save_button.addEventListener("click", saveConsultation);
  }

  if (save_patient_button) {
    save_patient_button.addEventListener("click", savePatient);
  }

  function saveConsultation() {
    var element_collection = document.getElementsByClassName("save");
    var elements_array = [].slice.call(element_collection);
    var element_values = new Map();

    elements_array.forEach((element) => {
      if (element.tagName == "SELECT" && element.multiple) {
        var selected = [...element.options]
          .filter((option) => option.selected)
          .map((option) => option.value);
        element_values.set(element.getAttribute("name"), selected);
      } else if (element.tagName == "INPUT" && element.value == 0) {
        element_values.set(element.getAttribute("name"), 0);
      } else if (element.getAttribute("name") == "radiothoraxNormal") {
        if (element.checked) {
          element_values.set("radiothoraxNormal", "Oui");
        }
      } else {
        element_values.set(element.getAttribute("name"), element.value);
      }
    });
    console.log(Object.fromEntries(element_values));

    // post the data to the corresponding url
    fetch(save_consultation_url, {
      method: "post",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(Object.fromEntries(element_values)),
    })
      .then((res) => res.json())
      .then((body) => {
        console.log(body);
      });
  }

  function savePatient() {
    if (document.getElementById("div-err")) {
      document
        .getElementById("div-err")
        .parentNode.removeChild(document.getElementById("div-err"));
    }

    var element_collection = document.getElementsByClassName("save");
    var elements_array = [].slice.call(element_collection);
    var element_values = new Map();

    elements_array.forEach((element) => {
      element_values.set(element.getAttribute("name"), element.value);
    });

    console.log(Object.fromEntries(element_values));
    fetch(save_patient_url, {
      method: "post",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(Object.fromEntries(element_values)),
    })
      .then((res) => {
        if (!res.ok) {
          console.log("An error occured saving the Patient");
        } else {
          // /* For deployment */
          // window.location.replace("../../templates/index_1.html");

          /* For developpment */
          window.location.replace("templates/index_1.html");
        }
      })
      .catch((error) => {
        console.log("Error occured saving the Patient");
        console.log(error);

        // create an error div
        var errDiv = document.createElement("div");
        var breakLine = document.createElement("br");
        var errText = document.createElement("p");
        var first_text = document.createTextNode(
          "An Error Occured Saving The Patient!!"
        );
        var second_text = document.createTextNode("Please try again!!");

        errDiv.id = "div-err";
        // styling
        errText.classList.add("error-text");

        errText.appendChild(first_text);
        errText.appendChild(breakLine);
        errText.appendChild(second_text);
        errDiv.appendChild(errText);

        document.body.appendChild(errDiv);
      });
  }

  function getOrd() {
    fetch("http://localhost:8080/Prescription/retrieve-all-Prescription")
      .then((response) => response.json())
      .then((data) => {
        const table = document.querySelector("#ordTable tbody");
        data.forEach((item) => {
          const row = table.insertRow();
          const cell1 = row.insertCell();
          cell1.textContent = item.Molécule;
          const cell2 = row.insertCell();
          cell2.textContent = item.Dose;
          const cell3 = row.insertCell();
          cell3.textContent = item.Unité;
          const cell4 = row.insertCell();
          cell4.textContent = item.JourProd;
        });
      })
      .catch((error) => console.error(error));
  }
});
