using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Entities
{
    public class Vehicle: BaseEntity
    {
        public Vehicle() 
        {
            HistoryVehicleParkPlaces = new List<HistoryVehicleParkPlace>();
        }

        public string id_type_vehicle { get; set; }
        public string mark { get; set; }
        public string model { get; set; }
        public string color { get; set; }
        public string license { get; set; }

        public virtual TypeVehicle TypeVehicle { get; set; }
        public virtual ICollection<HistoryVehicleParkPlace> HistoryVehicleParkPlaces { get; set; }
    }
}
