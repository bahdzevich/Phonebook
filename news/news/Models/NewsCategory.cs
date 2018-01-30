using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Newtonsoft.Json;

namespace news.Models
{
    [Table("news_has_news_category")]
    public class NewsCategory
    {
        [Column("news_id")]
        public long NewsId { get; set; }

        //[ForeignKey("NewsId")]
        [JsonIgnore]
        public News News { get; set; }

        [Column("news_category_id")]
        public long CategoryId { get; set; }

        //[ForeignKey("CategoryId")]
        [JsonIgnore]
        public Category Category { get; set; }

    }
}
