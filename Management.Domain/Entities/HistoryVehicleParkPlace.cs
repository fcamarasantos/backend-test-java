using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Entities
{
    public class HistoryVehicleParkPlace: BaseEntity
    {
        public int id_vehicle { get; set; }
        public int id_parking_place { get; set; }
        public DateTime date_in { get; set; }
        public DateTime date_out { get; set; }
        public TimeSpan time { get; set; }

        public virtual Vehicle Vehicle { get; set; }
        public virtual ParkingPlace ParkingPlace { get; set; }
    }
}
