using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Entities
{
    public class Establishment: BaseEntity
    {
        public Establishment()
        {
            ParkingPlaces = new List<ParkingPlace>(); 
        }

        public string name { get; set; }
        public string address { get; set; }
        public string fone { get; set; }
        public int qt_parking_place_car { get; set; }
        public int qt_parking_place_motorcycle { get; set; }

        public virtual ICollection<ParkingPlace> ParkingPlaces { get; set; }
    }
}
