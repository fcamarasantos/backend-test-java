using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Dtos
{
    public class RegisterDto
    {
        public string name { get; set; }
        public string email { get; set; }
        public string password { get; set; }
        public string fone { get; set; }
    }
}
