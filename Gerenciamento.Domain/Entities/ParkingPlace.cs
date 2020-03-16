using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Entities
{
    public class ParkingPlace: BaseEntity 
    {
        public ParkingPlace()
        {
            HistoryVehicleParkPlaces = new List<HistoryVehicleParkPlace>();
        }

        public int id_establishment { get; set; }
        public int id_vehicle { get; set; }
        public int id_column { get; set; }
        public int id_line { get; set; }
        public string description { get; set; }
        public DateTime date_in { get; set; }
        public DateTime date_out { get; set; }
        public TimeSpan time { get; set; }
        public bool avaliable { get; set; }

        public virtual Establishment Establishment { get; set; }
        public virtual Vehicle Vehicle { get; set; }
        public virtual ICollection<HistoryVehicleParkPlace> HistoryVehicleParkPlaces { get; set; }
    }
}
