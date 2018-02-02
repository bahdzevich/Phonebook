using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace news.Dto
{
    public class NewsRequestDto
    {
        [MaxLength(255)]
        [RegularExpression(pattern:"^([A-z0-9])+([ \"\'\\-\\.\\,\\?\\&A-Za-z0-9]*\\.)?$")]
        public string Title { get; set; }

        public string Content { get; set; }

        public long UserId { get; set; }

        public List<CategoryRequestDto> Categories { get; set; }
    }
}
