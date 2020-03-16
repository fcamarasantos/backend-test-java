using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Management.Domain.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ManagementEstablishment.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ParkingPlaceController : MainController, IBaseController
    {
        [HttpGet("get")]
        public IActionResult get(int? id = null)
        {
            return Ok();
        }

        [HttpPost]
        public IActionResult post<T>(T model)
        {
            return Ok();
        }

        [HttpPut("{id}")]
        public IActionResult put<T>(int id, T model)
        {
            return Ok();
        }

        [HttpPut("{id}")]
        public IActionResult remove<T>(int id)
        {
            return Ok();
        }
    }
}