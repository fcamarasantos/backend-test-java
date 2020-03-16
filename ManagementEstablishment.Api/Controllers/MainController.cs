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
    public abstract class MainController : ControllerBase
    {
        
    }
}
