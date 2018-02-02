using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace news.Dto
{
    public class CategoryRequestDto
    {
        [JsonRequired]
        public long Id { get; set; }

    }
}
