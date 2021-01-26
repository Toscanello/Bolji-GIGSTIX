Vue.component("prikazmanifestacije",{
    data: function(){
        return{
          manifestacija:null
        }
    },

    template:`
        <img :src="'../images/'+m.slika+'.jpg'" width = "300px" heigth = "300">
        <div class="card-body">
            <p class="card-text">Naziv: {{m.naziv}}<br/>Datum: {{m.datum.date.day}}.{{m.datum.date.month}}.{{m.datum.date.year}}
            u {{m.datum.time.hour}}:{{m.datum.time.minute}}<br/>
            Lokacija: {{m.lokacija.adresa.ulica}} {{m.lokacija.adresa.broj}}
            {{m.lokacija.adresa.mesto}} {{m.lokacija.adresa.postBroj}}<br/>Broj mesta: {{m.brojMesta}}<br/>
            Cena karte je: {{m.cena}}</p>
        </div>
    `,
    mounted(){
        this.manifestacija=JSON.parse(localStorage.getItem('manif'))
    },
})