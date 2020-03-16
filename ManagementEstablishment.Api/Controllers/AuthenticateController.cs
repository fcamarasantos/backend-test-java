using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Management.Domain.Dtos;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ManagementEstablishment.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthenticateController : ControllerBase
    {
        [HttpPost("login")]
        public IActionResult login( [FromBody] AuthenticateDto authenticate)
        {
            return Ok();
        }

        [HttpPost("register")]
        public IActionResult register([FromBody] AuthenticateDto register)
        {
            return Ok();
        }

    }
}