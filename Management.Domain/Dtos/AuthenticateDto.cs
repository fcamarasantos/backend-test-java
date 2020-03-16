using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Dtos
{
    public class AuthenticateDto
    {
        public string email { get; set; }
        public string password { get; set; }
    }
}
