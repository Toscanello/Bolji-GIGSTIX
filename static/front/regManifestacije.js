Vue.component("registracijamanifestacije",{
    data: function(){
        return{
            naziv: "",
            tip: "",
            brojMesta:"",
            datum:"",
            cena:"",
            status:"",
            lokacija:"",
            korisnik:"",
            file: ""
        }
    },

    template:`
        <form id="registrationForm" method ="POST" @submit.prevent = "submitForm">
            <div>
                <label for="naziv"><b>Naziv Manifestacije</b></label>
                <input type="text" v-model="naziv" placeholder = "Uneti ime manifestacije" required/>
            </div>
            <div>
                <label for="tip"><b>Tip Manifestacije</b></label>
                <input type="text" v-model="tip" placeholder = "Uneti tip manifestacije" required/>
            </div>
            <div>
                <label for="mesta"><b>Broj Mesta</b></label>
                <input type="text" v-model="brojMesta" placeholder = "Uneti broj mesta" required/>
            </div>
            <div>
                <label for="datum"><b>Datum</b></label>
                <input type="text" v-model="datum" placeholder = "Uneti datum manifestacije" required/>
            </div>
            <div>
                <label for="cena"><b>Cena</b></label>
                <input type="text" v-model="cena" placeholder = "Uneti cenu manifestacije" required/>
            </div>
            <div>
                <label for="lokacija"><b>Lokacija</b></label>
                <input type="text" v-model="lokacija" placeholder = "Example(44.58,41.25,ulica,broj,grad,postBroj)" required/>
            </div>
            <label>File
                <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" />
            </label>
            <div>
                <button type = "submit" class="btn btn-sm btn-outline-primary">Dodaj</button>
            </div>
        </form>
    `,mounted(){
        this.korisnik=JSON.parse(localStorage.getItem('korisnik'))
    },
    methods:{
        submitForm:function(){
            const manif = {
                    naziv: this.naziv,
                    tip: this.tip,
                    brojMesta: this.brojMesta,
                    datum: this.datum,
                    cena: this.cena,
                    status: "neaktivan",
                    lokacija: this.lokacija,
                    korisnik: this.korisnik.username,
                    putanjaDoSlike: this.file.name
            }

            this.submitFile();

            axios
            .post('/regManifestacije', manif)
            .then(response=>{
                this.$router.push('/home-page')
            })
            .catch(error=>{
                console.log("Greska.")
                
                alert("Greska pri unosu podataka.")
                //window.location.reload()

            })
           
        },
        submitFile() {
            let formData = new FormData();
            formData.append("file", this.file);
            console.log(">> formData >> ", formData);
      
            axios
              .post("/upload", formData, {
                headers: {
                  "Content-Type": "multipart/form-data",
                },
              })
              .then(function () {
                console.log("SUCCESS!!");
              })
              .catch(function () {
                console.log("FAILURE!!");
              });
          },
          handleFileUpload() {
            this.file = this.$refs.file.files[0];
            console.log(">>>> 1st element in files array >>>> ", this.file);
          },
    }
})