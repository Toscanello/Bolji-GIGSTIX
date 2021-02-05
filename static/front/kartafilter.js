Vue.component("karta-filter",{
    data: function(){
        return{
          pretraga:{
            
            reg:false,
            vip:false,
            fpit:false
            
          }
        }
    },

    template:`
        <div>
        
            <input type="checkbox" v-model="pretraga.reg">Regular</input>
            <input type="checkbox" v-model="pretraga.vip">VIP</input>
            <input type="checkbox" v-model="pretraga.fpit">Fan Pit</input>
            <button type="button" v-on:click="filter()">Filter</button>
            
        </div>
    `,
    methods: {
        filter: function(){
            this.$emit('clicked', this.pretraga)
        }
    }
})