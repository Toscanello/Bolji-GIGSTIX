Vue.component("komentarisanje",{
    data: function(){
        return{
          kom:{

              komentar:"",
              ocena:""

          }
        }
    },

    template:`
        <div>

            <h4> Uneti komentar: </h4>     
            <input v-model="kom.komentar" type="text" id="kom"/>
            <h4>Uneti ocenu</h4>
            <input type="number" v-model="kom.ocena" min="1" max="5"/>
            
            <button type="button" v-on:click="komentar()"> komentarisi </button>
           
        </div>
    `,
    methods: {
        komentar: function(){
            this.$emit('clicked', this.kom)
        }
    }
})