Vue.component("karta-sort",{
    data: function(){
        return{
          search:{
            
            rast:false,
            opad:false,
            dat_rast:false,
            dat_opad:false

          }
        }
    },

    template:`
        <div>
            <button type="button" v-on:click="rastfun()">Naziv (Rastuce)</button>
            <button type="button" v-on:click="opadfun()">Naziv (Opadajuce)</button>
            <button type="button" v-on:click="datRastfun()">Datum Rastuce</button>
            <button type="button" v-on:click="datOpadfun()">Datum Opadajuce</button>
            
        </div>
    `,
    methods: {
        rastfun: function(){
            this.search.rast = true
            this.search.opad = false
            this.search.dat_rast = false
            this.search.dat_opad = false
            console.log("Klik!")
            this.$emit('clicked', this.search)
        },
        opadfun: function(){
            this.search.rast = false
            this.search.opad = true
            this.search.dat_rast = false
            this.search.dat_opad = false
            console.log("Klik!")
            this.$emit('clicked', this.search)
        },
        datRastfun: function(){
            this.search.rast = false
            this.search.opad = false
            this.search.dat_rast = true
            this.search.dat_opad = false
            console.log("Klik!")
            this.$emit('clicked', this.search)
        },
        datOpadfun: function(){
            this.search.rast = false
            this.search.opad = false
            this.search.dat_rast = false
            this.search.dat_opad = true
            console.log("Klik!")
            this.$emit('clicked', this.search)
        }
    }
})