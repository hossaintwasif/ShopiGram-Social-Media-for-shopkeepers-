export const isLikedByReqUser=(reqUserid,post)=>{

    if (!post || !post.liked) {
        return false;
      }
      
    for(let user of post.liked){
        if(reqUserid===user.id){
            return true;
        }
    }
    return false;
}